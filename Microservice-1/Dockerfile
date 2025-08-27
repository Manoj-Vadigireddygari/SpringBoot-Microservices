
# Use lightweight Java image
FROM openjdk:17-jdk-slim

LABEL authors="manojvadigireddygari"

# Working directory inside container
WORKDIR /app

# Copy built jar into container
COPY target/manoj-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8081

# Run app
ENTRYPOINT ["java", "-jar", "app.jar"]