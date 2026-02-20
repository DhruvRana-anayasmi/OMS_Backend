# Build stage
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Run stage - Using Eclipse Temurin instead of the deprecated openjdk image
FROM eclipse-temurin:17-jre-alpine
COPY --from=build /target/*.jar app.jar
EXPOSE 8080

# Essential for Render Free Tier: Limit memory to prevent OOM crashes
ENTRYPOINT ["java", "-Xmx300m", "-Xss512k", "-jar", "/app.jar"]