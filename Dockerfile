# Build time
FROM gradle:jdk17
COPY --chown=gradle:gradle . /home/gradle/src
RUN ls /home/gradle/src/app/build/libs
RUN mkdir /app
COPY /home/gradle/src/app/build/libs/app.jar /app/spring-boot-application.jar
COPY ./scripts/wait-for.sh /wait-for.sh
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/spring-boot-application.jar"]