package ru.otus.otuskotlin.marketplace.e2e.be.docker

import ru.otus.otuskotlin.marketplace.e2e.be.fixture.docker.AbstractDockerCompose

object KtorLinuxPGDockerCompose : AbstractDockerCompose(
    "app-ktor",
    8080,
    "docker-compose-ktor-pg-linux.yml",
)
