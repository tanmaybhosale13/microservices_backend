FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Matching your exact pom.xml artifact version name
COPY target/api-gateway-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8770
ENTRYPOINT ["java", "-jar", "app.jar"]