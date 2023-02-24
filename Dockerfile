FROM gradle:latest AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test

FROM openjdk:latest
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/Phone-Book-API-0.0.1-SNAPSHOT.jar /app/
RUN bash -c 'touch /app/Phone-Book-API-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-jar", "/app/Phone-Book-API-0.0.1-SNAPSHOT.jar"]
