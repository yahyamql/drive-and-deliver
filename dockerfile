# Use the official Maven image to build the application
FROM maven:3.9.8-amazoncorretto-21-al2023 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file and download dependencies
COPY pom.xml /app/
RUN mvn dependency:go-offline

# Copy the source code
COPY src /app/src

# Build the application
RUN mvn package -DskipTests

# Use a minimal image with Java 21 for running the application
FROM eclipse-temurin:21-jre-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built application JAR from the builder stage
COPY --from=builder /app/target/*.jar /app/app.jar

# Expose the port that the application will run on
EXPOSE 8080

# Set the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]