FROM openjdk:11-jdk-slim
EXPOSE 8000
COPY "target/workvenue-backend.jar" app.jar
CMD [ "java","-Dspring.profiles.active=dev", "-jar", "app.jar" ]