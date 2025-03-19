FROM eclipse-temurin:17-jdk

WORKDIR /config-server

COPY ./target/*.jar config-server.jar

COPY src/main/resources/config /config

EXPOSE 11009

ENTRYPOINT ["java", "-jar", "config-server.jar"]