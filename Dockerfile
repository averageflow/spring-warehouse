# Build time
FROM gradle:jdk17
RUN mkdir /app
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon
WORKDIR /
RUN ls ./home/gradle/src/app/build/libs/
RUN ls ./app/
# RUN ls /home/gradle/src/app/build/libs
COPY ./home/gradle/src/app/build/libs/app.jar ./app/spring-boot-application.jar
COPY ./scripts/wait-for.sh /wait-for.sh
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/spring-boot-application.jar"]