FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Note: Check if your version is 1.0.0 or 0.0.1-SNAPSHOT in your pom.xml and match it here
COPY target/config-server-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "app.jar"]