package ru.otus.otuskotlin.marketplace.e2e.be.docker

import ru.otus.otuskotlin.marketplace.e2e.be.fixture.docker.AbstractDockerCompose

object KtorJvmFullDockerCompose : AbstractDockerCompose(
    "envoy", 8080, "docker-compose-ktor-full-x86.yml"
)
