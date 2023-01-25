FROM openjdk:11
EXPOSE 8000
COPY "target/workvenue-backend.jar" app.jar
CMD [ "java","-Dspring.profiles.active=test", "-jar", "app.jar" ]