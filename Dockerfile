FROM openjdk:8-jdk-alpine

LABEL maintainer="mikhail_zotov"

COPY target/MicroRedis*.jar /microservice-redis.jar

CMD ["java","-jar","/microservice-redis.jar"]