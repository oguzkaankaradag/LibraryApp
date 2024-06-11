# Use Maven image to build the project
FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

# Copy the pom.xml file and download dependencies
COPY pom.xml .
RUN mvn dependency:resolve dependency:resolve-plugins

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Use a minimal JDK image to run the application
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/app-0.0.1-SNAPSHOT.jar /app/app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
