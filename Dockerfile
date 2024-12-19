# Use an official OpenJDK image as the base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY target/your-springboot-app.jar app.jar

# Set environment variables for Render's deployment
# Render automatically sets PORT; use it for your application
ENV PORT=8080

# Expose the port the app runs on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "app.jar"]
