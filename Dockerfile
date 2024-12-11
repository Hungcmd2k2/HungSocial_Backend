# Stage 1: build
# Start with a Maven image that includes JDK 21
FROM maven:3.9.9-amazoncorretto-23-al2023 AS build

# Copy source code and pom.xml file to /app folder
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build source code with maven
RUN mvn package -DskipTests

#Stage 2: create image
# Start with Amazon Correto JDK 23
FROM amazoncorretto:23.0.1-al2023-headless

# Set working folder to App and copy complied file from above step
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]