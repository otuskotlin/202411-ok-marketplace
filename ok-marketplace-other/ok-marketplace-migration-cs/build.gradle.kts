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

val csContainer: ComposeContainer by lazy {
    ComposeContainer(
        file("src/test/compose/docker-compose-cs.yml")
    )
        .withExposedService("cassandra", 9042)
}

tasks {
    val buildImages by creating {
        dependsOn(docker)
    }

    val clean by creating {
        dependsOn(dockerClean)
    }

    val cassandraUp by creating {
        group = "db"
        doFirst {
            println("Starting Cassandra...")
            csContainer.start()
            println("Cassandra started at port: ${csContainer.getServicePort("cassandra", 9042)}")
        }
    }

    val cassandraDn by creating {
        group = "db"
        doFirst {
            println("Stopping Cassandra...")
            csContainer.stop()
            println("Cassandra stopped")
        }
    }
}
