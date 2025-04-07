package ru.otus.otuskotlin.marketplace.e2e.be.docker

import ru.otus.otuskotlin.marketplace.e2e.be.fixture.docker.AbstractDockerCompose

object KtorJvmCSDockerCompose : AbstractDockerCompose(
    "app-ktor", 8080, "docker-compose-ktor-cs-jvm.yml"
)
