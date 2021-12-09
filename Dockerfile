# Build time
FROM gradle:jdk17-alpine
RUN mkdir /home/gradle/buildWorkspace
COPY . /home/gradle/buildWorkspace

WORKDIR /home/gradle/buildWorkspace
RUN rm /home/gradle/buildWorkspace/app/src/main/resources/application.properties
RUN mv /home/gradle/buildWorkspace/app/src/main/resources/application-docker.properties /home/gradle/buildWorkspace/app/src/main/resources/application.properties
RUN gradle build --no-daemon
RUN tar -xvf /home/gradle/buildWorkspace/app/build/distributions/app.tar
EXPOSE 8080
ENTRYPOINT ["/home/gradle/buildWorkspace/app/build/distributions/app/bin/app"]