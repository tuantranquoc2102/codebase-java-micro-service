FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY ./target/*.jar auth-service.jar

EXPOSE 11002

ENTRYPOINT ["java", "-jar", "auth-service.jar"]