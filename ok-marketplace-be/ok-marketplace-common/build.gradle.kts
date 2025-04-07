plugins {
    id("build-kmp")
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                api(libs.kotlinx.datetime)
                implementation(libs.kotlinx.serialization.core)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.coroutines.core)
                api("ru.otus.otuskotlin.marketplace.libs:ok-marketplace-lib-logging-common")
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation(project(":ok-marketplace-api-v2-kmp"))
            }
        }

        jvmTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        nativeTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
