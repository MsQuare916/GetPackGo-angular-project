FROM openjdk:11-jre-slim
WORKDIR usr/src
EXPOSE 9000
ADD ./target/email-service.jar /usr/src/email-service.jar
ENTRYPOINT ["java","-jar","email-service.jar"]
