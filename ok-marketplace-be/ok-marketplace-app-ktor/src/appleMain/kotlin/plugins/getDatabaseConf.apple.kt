package ru.otus.otuskotlin.marketplace.app.ktor.plugins

import io.ktor.server.application.*
import ru.otus.otuskotlin.marketplace.app.ktor.configs.ConfigPaths
import ru.otus.otuskotlin.marketplace.common.repo.IRepoAd

actual fun Application.getDatabaseConf(type: AdDbType): IRepoAd {
    val dbSettingPath = "${ConfigPaths.repository}.${type.confName}"
    val dbSetting = environment.config.propertyOrNull(dbSettingPath)?.getString()?.lowercase()
    return when (dbSetting) {
        "in-memory", "inmemory", "memory", "mem" -> initInMemory()
        else -> throw IllegalArgumentException(
            "$dbSettingPath has value of '$dbSetting', but it must be set in application.yml to one of: " +
                    "'inmemory'"
        )
    }
}
