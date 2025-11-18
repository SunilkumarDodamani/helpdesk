# Step 1: Build the project using Maven with Java 25
FROM maven:3.9.3-eclipse-temurin-25 AS build

# Set working directory inside the container
WORKDIR /app

# Copy Maven configuration and source code
COPY pom.xml .
COPY src ./src

# Build the project (skip tests for faster build)
RUN mvn clean package -DskipTests

# Step 2: Run the app with Java 25 runtime
FROM eclipse-temurin:25-jre-alpine

# Set working directory
WORKDIR /app

# Copy the built jar from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8081

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
