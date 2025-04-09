package ru.otus.otuskotlin.marketplace.plugin

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.repositories
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

@Suppress("unused")
internal class BuildPluginJvm : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        pluginManager.apply("org.jetbrains.kotlin.jvm")

        val libs = project.the<LibrariesForLibs>()
        extensions.configure<KotlinJvmProjectExtension> {
            jvmToolchain(libs.versions.jvm.language.get().toInt())
        }

        group = rootProject.group
        version = rootProject.version
        repositories {
            mavenCentral()
        }
    }
}
