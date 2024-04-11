# Spring Learning Related

## Adding a project to GitHub

### Adding a folder as a GitHub project

```shell
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/Dhruvaraju/spring-learning.git
git push -u origin main
```

- `git init` to initiate a folder as a git repository
- `git add README.md` adding a file named `README.md` to staging
- `git commit -m "first commit"` adding a first commit.
- `git branch -M main` creating a branch called `main`
- `git remote add origin https://github.com/Dhruvaraju/spring-learning.git` setting the remote repo to which this folder
  should go.
- `git push -u origin main` for pushing code to branch main

## Pushing an existing repository to GitHub

```shell
git remote add origin https://github.com/Dhruvaraju/spring-learning.git
git branch -M main
git push -u origin main
```

## Creating a postgres database with docker container

```shell
docker run -d -p 5432:5432 -v postgres:/var/lib/postgresql/data -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=momentumdb --name postgres postgres
```

## Reference properties for postgres

```yml
server:
    port: 9600
    servlet:
        context-path: /users
    compression:
        enabled: true
        mime-types: application/json
        min-response-size: 1024
    http2:
        enabled: true
spring:
    application:
        name: APP_NAME
    mvc:
        throw-exception-if-n0-handler-found: true
    cloud:
        config:
            enabled: false
            import-check:
                enabled: false
    datasource:
        driver-class-name: org.postgresql.Driver
        url: jdbc:postgresql://${postgres.service.host}:${postgres.service.port}/${database.name}
        platform: postgres
        username: ${postgres.service.username}
        password: ${postgres.service.password}
        hikari:
            connectionTimeout: 20000
            maxLifetime: 600000
            maximumPoolSize: 10
            schema: backend
    jpa:
        database-platform: org.hibernate.dialect.PostgreSQLDialect
        hibernate:
            ddl-auto: update
        properties:
            hibernate:
                dialect: org.hibernate.dialect.PostgreSQLDialect
                format_sql: true
                lob:
                    non_contextual_creation: true
                temp:
                    use_jdbc_metadata_defaults: false
        show-sql: true
    profiles:
        active: default

database:
    name: DATABASE_NAME
postgres:
    service:
        host: localhost
        password: admin
        port: 5432
        username: admin


springdoc:
    api-docs:
        enable: true
        path: /v3/api-docs
        swagger-ui:
            path: /swagger-ui.html
    version: 1.0
```