# ---------- Etapa 1: Build con Gradle ----------
FROM eclipse-temurin:17-jdk-jammy AS builder
WORKDIR /app

# Copiar archivos de build de Gradle
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .

RUN chmod +x gradlew

# Descargar dependencias (cache)
RUN ./gradlew dependencies --no-daemon || true

# Copiar código fuente
COPY src src

# Compilar el JAR
RUN ./gradlew bootJar --no-daemon

# ---------- Etapa 2: Runtime ----------
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75.0"

# Copiar JAR generado
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]
