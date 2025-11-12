# ==========================
# Step 1: Build Stage (using Maven + Java 21)
# ==========================
FROM maven:3.9.9-eclipse-temurin-21 AS build

# Set working directory inside the container
WORKDIR /app

# Copy only pom.xml first (for dependency caching)
COPY pom.xml .

# Download all dependencies (helps with build caching)
RUN mvn dependency:go-offline -B

# Copy the rest of the project source
COPY src ./src

# Build the application (skip tests for faster build)
RUN mvn clean package -DskipTests

# ==========================
# Step 2: Run Stage (lightweight JDK 21)
# ==========================
FROM eclipse-temurin:21-jdk-alpine

# Create a working directory
WORKDIR /app

# Copy the built JAR file from the Maven build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (default Spring Boot port)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
