import io.ktor.plugin.features.*
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink

plugins {
    alias(libs.plugins.kotlinx.serialization)
    id("build-kmp")
//    id("io.ktor.plugin")
    alias(libs.plugins.ktor)
    alias(libs.plugins.palantir.docker)
}

application {
    mainClass.set("io.ktor.server.cio.EngineMain")
}

ktor {
    configureNativeImage(project)
    docker {
        localImageName.set("${project.name}-jvm")
        imageTag.set(project.version.toString())
        jreVersion.set(JavaVersion.VERSION_21)
    }
}

jib {
    container.mainClass = application.mainClass.get()
}

docker {
    name = "${project.name}-x64:${project.version}"

    // Файлы для Docker-контекста
    files(
        file("src/commonMain/resources/application.yml"),
    )

    // Путь к Dockerfile.X64 (если не в корне)
    setDockerfile(file("Dockerfile.X64"))

    // Аргументы сборки
    buildArgs(mapOf(
        "APP_VERSION" to project.version.toString()
    ))

    // Лейблы
    labels(mapOf(
        "maintainer" to "dev@example.com"
    ))
}

kotlin {
    // !!! Обязательно. Иначе не проходит сборка толстых джанриков в shadowJar
    jvm { withJava() }
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        binaries {
            executable {
                entryPoint = "ru.otus.otuskotlin.marketplace.app.ktor.main"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(libs.ktor.server.core)
                implementation(libs.ktor.server.cio)
                implementation(libs.ktor.server.cors)
                implementation(libs.ktor.server.yaml)
                implementation(libs.ktor.server.negotiation)
                implementation(libs.ktor.server.headers.response)
                implementation(libs.ktor.server.headers.caching)
                implementation(libs.ktor.server.websocket)

//                // Для того, чтоб получать содержимое запроса более одного раза
//                В Application.main добавить `install(DoubleReceive)`
//                implementation("io.ktor:ktor-server-double-receive:${libs.versions.ktor.get()}")

                implementation(project(":ok-marketplace-common"))
                implementation(project(":ok-marketplace-app-common"))
                implementation(project(":ok-marketplace-biz"))

                // v2 api
                implementation(project(":ok-marketplace-api-v2-kmp"))

                // Stubs
                implementation(project(":ok-marketplace-stubs"))
                // RabbitMQ
//                implementation(project(":ok-marketplace-app-rabbit"))

                implementation(libs.kotlinx.serialization.core)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.ktor.serialization.json)

                // DB
                implementation(libs.uuid)
                implementation(projects.okMarketplaceRepoCommon)
                implementation(projects.okMarketplaceRepoStubs)
                implementation(projects.okMarketplaceRepoInmemory)

                // logging
                implementation(project(":ok-marketplace-api-log1"))
                implementation("ru.otus.otuskotlin.marketplace.libs:ok-marketplace-lib-logging-common")
                implementation("ru.otus.otuskotlin.marketplace.libs:ok-marketplace-lib-logging-kermit")
                implementation("ru.otus.otuskotlin.marketplace.libs:ok-marketplace-lib-logging-socket")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))

                // DB
                implementation(projects.okMarketplaceRepoCommon)

                implementation(libs.ktor.server.test)
                implementation(libs.ktor.client.negotiation)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))

                // jackson
                implementation(libs.ktor.serialization.jackson)
                implementation(libs.ktor.server.calllogging)
                implementation(libs.ktor.server.headers.default)

                implementation(libs.logback)

                // transport models
                implementation(project(":ok-marketplace-api-v1-jackson"))
                implementation(project(":ok-marketplace-api-v1-mappers"))

                implementation("ru.otus.otuskotlin.marketplace.libs:ok-marketplace-lib-logging-logback")
                implementation(projects.okMarketplaceRepoPgjvm)
                implementation(libs.testcontainers.postgres)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }

        linuxX64Main {
            dependencies {
                implementation(projects.okMarketplaceRepoPgntv)
            }
        }
    }
}

tasks {
    shadowJar {
        isZip64 = true
    }

    // Если ошибка: "Entry application.yaml is a duplicate but no duplicate handling strategy has been set."
    // Возникает из-за наличия файлов как в common, так и в jvm платформе
    withType(ProcessResources::class) {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    val linkReleaseExecutableLinuxX64 by getting(KotlinNativeLink::class)
    val nativeFileX64 = linkReleaseExecutableLinuxX64.binary.outputFile
    val linuxX64ProcessResources by getting(ProcessResources::class)
    dockerPrepare {
        dependsOn(linkReleaseExecutableLinuxX64)
        dependsOn(linuxX64ProcessResources)
        group = "docker"
        this.destinationDir
        doFirst {
            copy {
                from(nativeFileX64)
                from(linuxX64ProcessResources.destinationDir)
                into(this@dockerPrepare.destinationDir)
            }
        }
    }
}
