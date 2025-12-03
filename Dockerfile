FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY . .
RUN ./mvnw -B clean package
CMD ["java", "-jar", "target/*.jar"]
