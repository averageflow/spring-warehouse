# Build time
FROM gradle:jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

# Run time
FROM openjdk:17-alpine
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/app.jar /app/spring-boot-application.jar
COPY ./scripts/wait-for.sh /wait-for.sh

ENTRYPOINT ["java", "-jar", "/app/spring-boot-application.jar"]