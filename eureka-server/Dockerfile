FROM eclipse-temurin:17-jdk

WORKDIR /eureka-server

COPY ./target/*.jar eureka-server.jar

COPY src/main/resources /eureka-server/resources
EXPOSE 8800

ENTRYPOINT ["java", "-jar", "eureka-server.jar"]