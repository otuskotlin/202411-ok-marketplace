pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        val kotestVersion: String by settings
        kotlin("jvm") version kotlinVersion
        kotlin("multiplatform") version kotlinVersion
        id("io.kotest.multiplatform") version kotestVersion
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "ok-marketplace-lessons"

include("m1l1-first")
include("m1l2-basic")
include("m1l3-func")
include("m1l4-oop")
include("m2l1-dsl")
include("m2l2-coroutines")
include("m2l3-flows")
include("m2l4-kmp")
include("m2l5-1-interop")
include("m2l5-2-jni")
include("m2l6-gradle")
include("m4l3-testing")

include(":m2l6-gradle:sub1:ssub1", ":m2l6-gradle:sub1:ssub2")

include(":m2l6-gradle-sub2")
project(":m2l6-gradle-sub2").apply {
    projectDir = file("m2l6-gradle/sub2")
    name = "m2l6-custom-sub2"
}

// Включает вот такую конструкцию
//implementation(projects.m2l6Gradle.sub1.ssub1)
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
