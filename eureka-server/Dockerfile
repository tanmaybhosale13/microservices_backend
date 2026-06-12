# Step 1: Use a lightweight Java 21 runtime image
FROM eclipse-temurin:21-jre-alpine

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the compiled jar from your local target folder into the container
COPY target/eureka-server-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose Eureka's default port
EXPOSE 8761

# Step 5: Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]