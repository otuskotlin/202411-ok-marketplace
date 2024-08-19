package ru.otus.otuskotlin.marketplace.plugin

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.repositories
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

@Suppress("unused")
internal class BuildPluginJvm : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        pluginManager.apply("org.jetbrains.kotlin.jvm")

        val libs = project.the<LibrariesForLibs>()
        tasks.withType(JavaCompile::class.java) {
            sourceCompatibility = libs.versions.jvm.language.get()
            targetCompatibility = libs.versions.jvm.compiler.get()
        }
        tasks.withType(KotlinJvmCompile::class.java).configureEach {
            compilerOptions {
                jvmTarget.set(JvmTarget.valueOf("JVM_" + libs.versions.jvm.compiler.get()))
            }
        }
        group = rootProject.group
        version = rootProject.version
        repositories {
            mavenCentral()
        }
    }
}
