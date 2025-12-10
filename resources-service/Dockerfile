FROM eclipse-temurin:21-jdk-jammy AS builder

WORKDIR /app

# Copiar TODO el proyecto primero
COPY . .

RUN chmod +x gradlew

# Compilar
RUN ./gradlew clean build -x test --no-daemon

# ---- Stage final ----
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","app.jar"]
