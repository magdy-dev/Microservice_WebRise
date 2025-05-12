# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the compiled .jar file to the container
COPY target/usersubscription-service.jar /app/usersubscription-service.jar

# Run the JAR file
ENTRYPOINT ["java", "-jar", "usersubscription-service.jar"]
