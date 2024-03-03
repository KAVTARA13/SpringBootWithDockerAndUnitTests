# Use OpenJDK 17 as base image
FROM openjdk:17-oracle

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/CRUDForDocker-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8181

# Run the application when the container starts
CMD ["java", "-jar", "app.jar"]