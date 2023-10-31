FROM openjdk:11-jre-slim
WORKDIR usr/src
EXPOSE 8082
ADD ./target/User-Service.jar /usr/src/User-Service.jar
ENTRYPOINT ["java","-jar","User-Service.jar"]