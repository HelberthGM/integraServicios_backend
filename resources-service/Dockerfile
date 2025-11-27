# ---------- Etapa 1: Build con Gradle ----------
FROM eclipse-temurin:25-jdk-jammy AS builder
WORKDIR /app

# Copiar archivos de build gradle
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .

# Descargar dependencias (cache)
RUN ./gradlew dependencies --no-daemon || true

# Copiar código
COPY src src

# Compilar JAR
RUN ./gradlew bootJar --no-daemon

# ---------- Etapa 2: Runtime ----------
FROM eclipse-temurin:25-jdk-jammy
WORKDIR /app

ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75.0"

# Copiar JAR generado
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
