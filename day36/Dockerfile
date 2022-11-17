# Build Angular first
FROM node:19 AS angular-build

WORKDIR /frontend

# Copy angular sources
COPY goodreads/angular.json .
COPY goodreads/karma.conf.js .
COPY goodreads/package.json .
COPY goodreads/package-lock.json .
COPY goodreads/tsconfig.app.json .
COPY goodreads/tsconfig.json .
COPY goodreads/tsconfig.spec.json .
COPY goodreads/src src

# Install Angular CLI
RUN npm install -g @angular/cli

# Install package
RUN npm ci

# Build Angular
RUN ng build

# Build SpringBoot
FROM maven:3-openjdk-18 AS java-build

WORKDIR /backend

# Copy Java sources
COPY backend/mvnw .
COPY backend/mvnw.cmd .
COPY backend/pom.xml .
COPY backend/src src

# Copy Angular files to SpringBoot
COPY --from=angular-build /frontend/dist/goodreads/ /backend/src/main/resources/static

# Build SpringBoot application
RUN mvn package -Dmaven.test.skip=true

# Copy Compiled SpringBoot application into running container
FROM openjdk:18-oracle

WORKDIR /app

# Copy JAR to this container
COPY --from=java-build /backend/target/backend-0.0.1-SNAPSHOT.jar backend-0.0.1-SNAPSHOT

# PORT environment required from Heroku/Railway
ENV PORT=8080

# Environment variables for database
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/goodreads
ENV SPRING_DATASOURCE_USERNAME=fred
ENV SPRING_DATASOURCE_PASSWORD=fred

# Run the application. If running in Heroku, need to set the environment variable PORT 
# to server.port
ENTRYPOINT java -Dserver.port=${PORT} -jar backend-0.0.1-SNAPSHOT
