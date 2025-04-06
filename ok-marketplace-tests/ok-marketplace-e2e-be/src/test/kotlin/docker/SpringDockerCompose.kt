package ru.otus.otuskotlin.marketplace.e2e.be.docker

import ru.otus.otuskotlin.marketplace.e2e.be.fixture.docker.AbstractDockerCompose

object SpringDockerCompose : AbstractDockerCompose(
    "app-spring", 8080, "docker-compose-spring-pg.yml"
)
