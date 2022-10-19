FROM maven:3-openjdk-18 AS builder

# create a working directory
WORKDIR /app

# Copy the files into the container
COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY src src

# build 
RUN mvn package -Dmaven.test.skip=true

FROM openjdk:18-oracle

WORKDIR /app

COPY --from=builder /app/target/day8-weather-0.0.1-SNAPSHOT.jar day8-weather.jar

# environment variable
ENV OPEN_WEATHER_MAP="set this"
ENV SERVER_PORT=8080

EXPOSE ${SERVER_PORT}

ENTRYPOINT java -jar day8-weather.jar