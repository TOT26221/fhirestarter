# Build stage for Maven
FROM maven:3.9.9-amazoncorretto-21 AS build
LABEL authors="pault"
WORKDIR /build
COPY src .
COPY pom.xml .
RUN mvn clean package

# Build stage for Node.js and npm
FROM node:20 AS node-build
WORKDIR /app
COPY spengerclient/package*.json ./
RUN npm install
COPY spengerclient/ .
RUN npm run build

# Final stage
FROM amazoncorretto:21
WORKDIR /app
COPY --from=build /build/target/*.jar app.jar
COPY --from=node-build /app/dist /app/dist
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]