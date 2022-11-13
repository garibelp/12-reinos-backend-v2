# Twelve Kingdoms API

Backend Application for RPG System 12 Kingdoms V2

## Requirements

For building and running the application you need:

- [Java SDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3](https://maven.apache.org)
- [Postgres SQL](https://www.postgresql.org/)

## Running the application locally

There is no need to set environment variables on the application if it is running using the profile `dev`.

To use the default configuration set on `application-dev.yml`, create a Postgres SQL database with the following
properties:

- **Database URL:** localhost:5432
- **Name:** twelve-kingdoms
- **Username:** postgres
- **Password:** postgres

If there is the need to change those variables, just change their values on the file to the ones being used locally.

Compile the application locally with the following command:

```shell
mvn clean install
```

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method
in the `br.com.extratora.twelvekingdoms.Main` class from your IDE and add value `--spring.profiles.active=dev` to
the Environment Variables.

Alternatively you can use
the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html)
like so:

```shell
# Command to run with development profile
mvn spring-boot:run -Dspring.profiles.active=dev
```

## Accessing Swagger documentation

With the application running, access the following URLs:

- [Swagger UI](http://localhost:8080/swagger-ui/index.html#/)
- [API Docs](http://http://localhost:8080/api-docs)