plugins {
    id("build-jvm")
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("ru.otus.otuskotlin.marketplace:ok-marketplace-api-v1-jackson")
    implementation("ru.otus.otuskotlin.marketplace:ok-marketplace-api-v2-kmp")

    testImplementation(libs.logback)
    testImplementation(libs.kermit)

    testImplementation(libs.bundles.kotest)

    testImplementation(libs.testcontainers.core)
    testImplementation(libs.coroutines.core)

    testImplementation(libs.ktor.client.core)
    testImplementation(libs.ktor.client.okhttp)
}

var severity: String = "MINOR"

tasks {
    withType<Test>().configureEach {
        useJUnitPlatform()
//        dependsOn(gradle.includedBuild(":ok-marketplace-app-spring").task("dockerBuildImage"))
//        dependsOn(gradle.includedBuild(":ok-marketplace-be").task(":ok-marketplace-app-ktor:publishImageToLocalRegistry"))
//        dependsOn(gradle.includedBuild(":ok-marketplace-app-rabbit").task("dockerBuildImage"))
//        dependsOn(gradle.includedBuild(":ok-marketplace-app-kafka").task("dockerBuildImage"))
    }
}
