import org.testcontainers.containers.ComposeContainer

plugins {
//    id("com.palantir.docker") version "0.36.0"
    alias(libs.plugins.palantir.docker)
}

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        // Testcontainers core + Docker Compose модуль
        // classpath("org.testcontainers:testcontainers:1.20.6")
        classpath(libs.testcontainers.core)
    }
}

group = "ru.otus.otuskotlin.marketplace.migration"
version = "0.1.0"

docker {
    name = "${project.name}:${project.version}"
//    tag("latestTag","latest")

    // Файлы для Docker-контекста
    files(fileTree("src/main/liquibase"))

    // Путь к Dockerfile (если не в корне)
    setDockerfile(file("src/main/docker/Dockerfile"))

    // Аргументы сборки
    buildArgs(mapOf(
        "APP_VERSION" to project.version.toString()
    ))

    // Лейблы
    labels(mapOf(
        "maintainer" to "dev@example.com"
    ))
}

val pgContainer: ComposeContainer by lazy {
    ComposeContainer(
        file("src/test/compose/docker-compose-pg.yml")
    )
        .withExposedService("psql", 5432)
}

tasks {
    val buildImages by creating {
        dependsOn(docker)
    }

    val clean by creating {
        dependsOn(dockerClean)
    }

    val pgDn by creating {
        group = "db"
        doFirst {
            println("Stopping PostgreSQL...")
            pgContainer.stop()
            println("PostgreSQL stopped")
        }
    }
    val pgUp by creating {
        group = "db"
        doFirst {
            println("Starting PostgreSQL...")
            pgContainer.start()
            println("PostgreSQL started at port: ${pgContainer.getServicePort("psql", 5432)}")
        }
        finalizedBy(pgDn)
    }
}
