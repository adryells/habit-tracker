FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/demo-0.0.1-SNAPSHOT.jar habit-tracker.jar
ENTRYPOINT ["java", "-jar", "habit-tracker.jar"]