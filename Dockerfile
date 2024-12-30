# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:21

# Set the working directory in the container
WORKDIR /app

# Add the Spring Boot JAR to the container
COPY target/josephus-api-1.0-SNAPSHOT.jar josephus-api.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "josephus-api.jar"]
