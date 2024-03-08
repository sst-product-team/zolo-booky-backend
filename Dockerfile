FROM openjdk:17-slim

COPY . .

RUN ./gradlew clean build

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "./build/libs/booky-0.0.1-SNAPSHOT.jar"]