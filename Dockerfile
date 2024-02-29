FROM openjdk:17

WORKDIR /zolo-booky-backend

COPY ./build/libs/booky-0.0.1-SNAPSHOT.jar ./booky.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "./booky.jar"]