FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY ./target/*.jar mongo-service.jar

EXPOSE 11004

ENTRYPOINT ["java", "-jar", "mongo-service.jar"]