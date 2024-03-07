FROM gradle:8.5.0-jdk17-alpine

COPY . .

RUN gradle clean build

COPY ./build/libs/booky-0.0.1-SNAPSHOT.jar ./booky.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "./booky.jar"]