FROM gradle:jdk17-alpine
RUN mkdir /home/gradle/buildWorkspace
COPY . /home/gradle/buildWorkspace
WORKDIR /home/gradle/buildWorkspace

# Use Docker application.properties with environment variables
RUN rm /home/gradle/buildWorkspace/app/src/main/resources/application.properties
RUN mv /home/gradle/buildWorkspace/app/src/main/resources/application-docker.properties /home/gradle/buildWorkspace/app/src/main/resources/application.properties

# Perform Gradle build
RUN gradle build --no-daemon

# Extract packaged application
RUN tar -xvf /home/gradle/buildWorkspace/app/build/distributions/app.tar -C /home/gradle/buildWorkspace/app/build/distributions

EXPOSE 8080
ENTRYPOINT ["/home/gradle/buildWorkspace/app/build/distributions/app/bin/app"]
