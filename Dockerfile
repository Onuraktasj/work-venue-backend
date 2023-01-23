FROM openjdk:11
EXPOSE 8000
COPY "target/work-venue-v1.jar" app.jar
CMD [ "java", "-jar", "app.jar" ]