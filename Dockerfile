FROM eclipse-temurin:17-jdk-alpine
ADD /docker/backend-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java","-jar","backend.jar"]
