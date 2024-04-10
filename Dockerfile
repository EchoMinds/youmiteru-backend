#FROM eclipse-temurin:17-jdk-alpine
FROM mcr.microsoft.com/openjdk/jdk:17-distroless
ADD /docker/*.jar backend.jar
ENTRYPOINT ["java","-jar","backend.jar"]