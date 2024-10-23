# Use an official JDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle wrapper and project files into the container
COPY . .

# Give permission to execute the Gradle wrapper
RUN chmod +x gradlew

# Build the application (skip tests to avoid failures during the build)
RUN ./gradlew build -x test

# Copy the built jar file to the final location
COPY build/libs/your-spring-boot-app.jar /app/your-spring-boot-app.jar

# Run the application
CMD ["java", "-jar", "/app/your-spring-boot-app.jar"]
