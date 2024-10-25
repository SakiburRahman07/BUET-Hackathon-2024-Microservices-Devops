# Use an official OpenJDK image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the build artifact from the build stage
COPY target/*.jar app.jar

# Expose the port that your application runs on
EXPOSE 8084

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
