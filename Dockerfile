FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --form=build /target/*.jar todo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "todo.jar"]