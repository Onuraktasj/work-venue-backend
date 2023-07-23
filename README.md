# Work Venue Backend Project

## URL BASE
* Backend Plan: https://drive.google.com/file/d/111ybsoDb46_cOtGE5_DnT4cCcvcJU28M/view?usp=share_link
* Planning: https://trello.com/b/xFYOsTWf/sprint-planning
* Sonarcloud: https://sonarcloud.io/project/overview?id=mertbahardogan_work-venue-backend
* Swagger Local: http://localhost:8000/api/v1/swagger-ui/
* Swagger Test: http://localhost:8000/api/v1/swagger-ui/

## Utils
str@gmail.com string1

https://medium.com/@ievgen.degtiarenko/reduce-size-of-docker-image-with-spring-boot-application-2b3632263350
* Spring-Dockerize:
1- docker build -t service-wv .
2- docker run --name=service-wv -dp 8000:8000 service- wv

* Postgres:
1- docker run --name postgresWV -e POSTGRES_USER=workvenue -e POSTGRES_PASSWORD=1357 -p 5432:5432 postgres
2- docker exec -it postgresWV psql -U workvenue -c "CREATE DATABASE work_venue;

## APIs Design
* Visitor 
* Venue
* Post
* Event
* Login

## Branch Rules 
* mb/feature/create-swagger
* mb/bugfix/visitor-service-uuid
* mb/refactor/user-service

### TODO List
* fix Exceptions and add ELK, all strings must be in class.
* fix pom.xml
* NPE check with Spring StringUtils class
* Handler util method
* controller ad. util method-by
* unit test/integration test
* all req/res has to be implemented serial things.
* upgrade new spring vers.

### Target List
* ELK, Kafka, Docker Size, Docker Compose, K8S, Rest Calls, Jaeger

### Happy Target List
* load test, grafana-promet, ssh and project sec., version flags.

### Currenct Tech Stack
* Spring Boot, Security, Docker, GitHub Actions CI/CD, SonarCloud, PostgreSQL, JUnit5 - Mockito (Unit/Integration Tests), Swagger

