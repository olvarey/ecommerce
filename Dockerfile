# syntax=docker/dockerfile:1

ARG SERVICE
ARG PORT

FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /build
COPY . .
RUN mvn -q -pl ${SERVICE} -am package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /build/${SERVICE}/target/${SERVICE}-0.0.1-SNAPSHOT.jar app.jar
EXPOSE ${PORT}
ENTRYPOINT ["java","-jar","/app/app.jar"]
