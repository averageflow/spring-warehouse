FROM ligaard/jdk17-gradle73:latest

COPY ./scripts/wait-for.sh /wait-for.sh

RUN mkdir -p /app/
RUN ./gradlew build
RUN tar -xvf /app/build/distributions/app.tar
RUN ls  /app/build/distributions/
ADD app/build/libs/app.jar /app/build/libs/app.jar
ENTRYPOINT ["java", "-jar", "/app/build/libs/app.jar"]
EXPOSE 8080/tcp