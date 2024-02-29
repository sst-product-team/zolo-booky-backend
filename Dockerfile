FROM openjdk:17

WORKDIR /zolo-booky-backend

COPY ./app.jar ./booky.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "./booky.jar"]