FROM openjdk:11-jre-slim
WORKDIR usr/src
ADD ./target/Eureka-server.jar /usr/src/Eureka-server.jar
ENTRYPOINT ["java","-jar","Eureka-server.jar"]
