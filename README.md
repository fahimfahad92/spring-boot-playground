# Spring Boot Playground

Small, focused Spring Boot 3 projects on Java 21. Each folder is an independent Maven project with its own wrapper, README and CI — open the folder, not the repo root.

| Project | What it demonstrates | CI |
|---|---|---|
| [`mysql-redis-sqs-template`](mysql-redis-sqs-template) | Service template wired to MySQL (JDBC), Redis and SQS; rate limiting; Testcontainers + LocalStack integration tests; multi-stage Dockerfile | [![build](https://github.com/fahimfahad92/spring-boot-playground/actions/workflows/mysql-redis-sqs-template.yml/badge.svg)](https://github.com/fahimfahad92/spring-boot-playground/actions/workflows/mysql-redis-sqs-template.yml) |
| [`jpa-optimistic-locking`](jpa-optimistic-locking) | Spring Data JPA: Hikari pooling, auditing, projections, optimistic locking under concurrent load (JMeter plan included), global exception handling | [![build](https://github.com/fahimfahad92/spring-boot-playground/actions/workflows/jpa-optimistic-locking.yml/badge.svg)](https://github.com/fahimfahad92/spring-boot-playground/actions/workflows/jpa-optimistic-locking.yml) |
| [`graphql-jwt`](graphql-jwt) | Spring for GraphQL: queries, mutations, input types, schema split per domain, error handling, JWT-secured API, integration tests | [![build](https://github.com/fahimfahad92/spring-boot-playground/actions/workflows/graphql-jwt.yml/badge.svg)](https://github.com/fahimfahad92/spring-boot-playground/actions/workflows/graphql-jwt.yml) |
| [`docker-compose-stacks`](docker-compose-stacks) | Reusable local-dev Compose stacks: MySQL, Redis, MySQL + Redis, with init scripts and file-based secrets | — |

**Stack:** Java 21 · Spring Boot 3.4 · Maven · MySQL · Redis · AWS SQS · GraphQL · Docker Compose · Testcontainers

## Running a project

```bash
cd jpa-optimistic-locking
docker compose -f docker-compose/compose.yaml up -d   # if the project has a compose file
./mvnw spring-boot:run
```

See each folder's README for details.

## History

These projects started as separate repositories and were merged here with full commit history:
`java-21-spring-boot-3`, `java-21-spring-boot-3-jpa`, `spring-boot-graphql`, `docker-compose-rnd`.

## License

[MIT](LICENSE)
