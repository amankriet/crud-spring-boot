# Stage 1: Build the application
FROM gradle:jdk23-corretto AS build

WORKDIR /app

COPY . .

# Ensure gradlew is executable
RUN chmod +x ./gradlew

# Check the contents of the directory
RUN ls -la

RUN ./gradlew build --no-daemon -x test

# Stage 2: Create the final image
FROM amazoncorretto:23-jdk

WORKDIR /app

COPY --from=build /app/build/libs/*-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]