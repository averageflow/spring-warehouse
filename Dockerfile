FROM gradle:jdk17-alpine
RUN mkdir /home/gradle/buildWorkspace
COPY . /home/gradle/buildWorkspace
WORKDIR /home/gradle/buildWorkspace

# Use Docker application.properties with environment variables
RUN rm /home/gradle/buildWorkspace/app/src/main/resources/application.properties
RUN mv /home/gradle/buildWorkspace/app/src/main/resources/application-docker.properties /home/gradle/buildWorkspace/app/src/main/resources/application.properties

# Perform Gradle build
RUN gradle bootJar --no-daemon

WORKDIR /home/gradle/buildWorkspace/app/build/libs

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app-1.0.0.jar"]
