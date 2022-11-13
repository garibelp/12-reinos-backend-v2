# twelve-kingdoms

Backend Application for RPG System 12 Kingdoms V2

## Requirements

For building and running the application you need:

- [Java SDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3](https://maven.apache.org)
- [Postgres SQL](https://www.postgresql.org/)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method
in the `de.codecentric.springbootsample.Application` class from your IDE and add value `--spring.profiles.active=dev` to
the Environment Variables.

Alternatively you can use
the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html)
like so:

```shell
# Command to run development configuration
mvn spring-boot:run -Dspring.profiles.active=dev
```