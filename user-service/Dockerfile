FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY ./target/*.jar user-server.jar

EXPOSE 11001

ENTRYPOINT ["java", "-jar", "user-server.jar"]