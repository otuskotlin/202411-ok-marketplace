package ru.otus.otuskotlin.marketplace.backend.repo.postgresql

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.toKString
import platform.posix.getenv

@OptIn(ExperimentalForeignApi::class)
fun getEnv(name: String): String? = getenv(name)?.toKString()
