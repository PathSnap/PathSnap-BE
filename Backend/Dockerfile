# Use a minimal base image for running Java applications
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the built .jar file from the host machine to the container
COPY build/libs/Backend-0.0.1-SNAPSHOT.jar /app/Backend-0.0.1-SNAPSHOT.jar

# Run the application
CMD ["java", "-jar", "Backend-0.0.1-SNAPSHOT.jar"]
