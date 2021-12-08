# Build time
FROM gradle:jdk17-alpine
RUN mkdir /home/gradle/buildWorkspace
COPY . /home/gradle/buildWorkspace

WORKDIR /home/gradle/buildWorkspace
RUN ls /home/gradle/buildWorkspace
RUN gradle build --no-daemon
RUN ls /home/gradle/buildWorkspace/app/build/distributions/
RUN tar -xvf /home/gradle/buildWorkspace/app/build/distributions/app.tar
COPY ./scripts/wait-for.sh /wait-for.sh
EXPOSE 8080
# ENTRYPOINT ["/home/gradle/buildWorkspace/app/build/distributions/app/bin/app"]