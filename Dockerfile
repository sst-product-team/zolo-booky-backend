FROM gradle:8.5.0-jdk17-alpine

COPY . .

RUN gradle build

COPY ./build/libs/booky-0.0.1-SNAPSHOT.jar ./booky.jar

RUN gradle clean

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "./booky.jar"]