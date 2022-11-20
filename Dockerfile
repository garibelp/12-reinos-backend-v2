FROM openjdk:17-jdk-alpine
RUN mkdir /work
WORKDIR /work
COPY /target/twelve-kingdoms-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/work/app.jar"]