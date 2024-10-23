# Use an official JDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the built jar file to the final location
COPY build/libs/Backend-0.0.1-SNAPSHOT.jar /app/Backend-0.0.1-SNAPSHOT.jar

# Run the application
CMD ["java", "-jar", "/app/Backend-0.0.1-SNAPSHOT.jar"]
