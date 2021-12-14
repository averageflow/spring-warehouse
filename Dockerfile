FROM gradle:jdk17-alpine AS build-env
WORKDIR /app

COPY . ./

RUN mv app/src/main/resources/application-docker.properties app/src/main/resources/application.properties

RUN gradle bootJar --no-daemon


FROM openjdk:17-alpine

COPY --from=build-env /app/app/build/libs/app-1.0.0.jar /app/app.jar

WORKDIR /app

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
