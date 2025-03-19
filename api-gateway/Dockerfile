FROM eclipse-temurin:17-jdk

WORKDIR /api-gateway

COPY ./target/*.jar api-gateway.jar
COPY ./src/main/resources/application.yml /api-gateway/config/application.yml
COPY ./src/main/resources/boostrap.yml /api-gateway/config/boostrap.yml
EXPOSE 11000

ENTRYPOINT ["java", "-jar", "api-gateway.jar",  "--spring.config.location=file:/api-gateway/config/application.yml"]
