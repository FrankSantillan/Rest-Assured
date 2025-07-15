# Use official Maven + JDK 17 image
FROM maven:3.9.0-eclipse-temurin-17

WORKDIR /app

# Copy pom.xml and download dependencies first (cache)
COPY pom.xml .

RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Copy config
COPY src/test/resources/config.properties ./src/test/resources/config.properties

# Run tests and generate allure results
CMD ["mvn", "clean", "test"]