FROM openjdk:11-jre-slim
WORKDIR usr/src
EXPOSE 8080
ADD ./target/api-gateway.jar /usr/src/api-gateway.jar
ENTRYPOINT ["java","-jar","api-gateway.jar"]