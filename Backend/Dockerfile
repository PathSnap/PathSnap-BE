# Use a minimal base image for running Java applications
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the built .jar file from the host machine to the container
COPY build/libs/your-spring-boot-app.jar /app/your-spring-boot-app.jar

# Run the application
CMD ["java", "-jar", "your-spring-boot-app.jar"]
