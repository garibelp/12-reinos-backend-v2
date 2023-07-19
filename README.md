# Twelve Kingdoms API

Backend Application for RPG System 12 Kingdoms V2

## Requirements

For building and running the application you need:

- [Java SDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3](https://maven.apache.org)
- [Postgres SQL](https://www.postgresql.org/)

## Running the application locally

### - Application profiles

There is no need to set environment variables on the application if it is running using the profile `dev`.

### <a name="dev-config"></a> - Application DEV environment

To use the default configuration set on `application-dev.yml`, create a Postgres SQL database with the following
properties or execute the docker-compose file on the root of the repository:

- **Database URL:** localhost:5432
- **Name:** twelve-kingdoms
- **Username:** postgres
- **Password:** postgres

### - Docker compose for application execution

If the user is using docker-compose, there a dockerfile on the project root with both adminer and PostgreSQL already
configured for the dev profile. To run the stack, just execute the following command on the root folder of the project:

```shell
$ docker-compose up -d
```

To access the Adminer UI, just open `http://localhost:8081` and connect to the database with the default database info
described on the [Application DEV environment](#dev-config) section.

If there is the need to change those variables, just change their values on the file to the ones being used locally.

### - Code quality - SonarQube

To keep the quality of the code, sonarqube is needed to check the coverage and find code smells. The docker-compose file
already creates a sonar instance to be used.

To use the sonar, follow the steps:

- Access `http://localhost:9000` and login with the default user `admin` and password `admin`
- Change the password on the first login
- Execute command on the project:

```shell
# Change value NEW_PWD to the updated password after the first login
$ mvn clean jacoco:prepare-agent install jacoco:report -Dsonar.host.url=http://localhost:9000 -Dsonar.log.level=TRACE
 -Dsonar.verbose=true -Dsonar.login=admin -Dsonar.password=NEW_PWD -Dsonar.projectName=twelve-kingdoms-api sonar:sonar
```

In case of failure of sonar not starting due to vm.max_map_count too low error in Windows, execute the following
commands in the Windows powershell:

```shell
# Command to 
$ wsl -d docker-desktop
$ sysctl -w vm.max_map_count=262144
```

### - Executing Backend

Compile the application locally with the following command:

```shell
$ mvn clean install
```

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method
in the `br.com.extratora.twelvekingdoms.Main` class from your IDE and add value `--spring.profiles.active=dev` to
the Environment Variables.

Alternatively you can use
the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html)
like so:

```shell
# Command to run with development profile
$ mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## Accessing Swagger documentation

With the application running, access the following URLs:

- [Swagger UI](http://localhost:8080/swagger-ui/index.html#/)
- [API Docs](http://localhost:8080/api-docs)

## Versions changelog

### - Release 1.4.0

- Added death roll tests

### - Release 1.3.0

- Added sheet wounds support

### - Release 1.2.0

- Added level up support

### - Release 1.1.0

- Added dungeon master role
- Added campaign CRUD support

### - Release 1.0.0

- User signin/signup
- Added the character CRUD

## Trello Board

- [12 Kingdoms](https://trello.com/b/wKoZUTPq/12-reinos-v2)
