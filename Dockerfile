FROM maven:3.9.9-amazoncorretto-21 AS build
LABEL authors="pault"
#dateien in den Container kopieren
WORKDIR /build
COPY src .
#jar datein erstellen
RUN mvn clean package
FROM amazoncorretto:21
WORKDIR /app
COPY --from=build /build/target/*.jar app.jar
EXPOSE 8080
#Container starten
ENTRYPOINT ["java", "-jar", "app.jar"]