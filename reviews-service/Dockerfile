# ---------- Etapa 1: Build con Gradle ----------
FROM eclipse-temurin:25-jdk-jammy AS builder
WORKDIR /app

# Copiar archivos de configuración de Gradle
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .

# Hacer ejecutable gradlew
RUN chmod +x gradlew

# Descargar dependencias (cache)
RUN ./gradlew build -x test --no-daemon

# Copiar código fuente
COPY src src

# Construir el JAR final
RUN ./gradlew bootJar -x test --no-daemon

# ---------- Etapa 2: Runtime ----------
FROM eclipse-temurin:25-jdk-jammy
WORKDIR /app

# Opciones optimizadas para contenedores
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75.0"

# Copiar el JAR desde la etapa anterior
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
