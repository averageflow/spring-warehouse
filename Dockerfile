FROM gradle:jdk17-alpine AS build-env
WORKDIR /app

COPY . ./

RUN mv app/src/main/resources/application-docker.yml app/src/main/resources/application.yml

RUN gradle bootJar --no-daemon


FROM eclipse-temurin:17.0.1_12-jre-alpine

COPY --from=build-env /app/app/build/libs/app-1.0.0.jar /app/app.jar

WORKDIR /app

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
