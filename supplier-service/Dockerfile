FROM openjdk:11-jre-slim
WORKDIR usr/src
EXPOSE 8087
ADD ./target/supplier-service.jar /usr/src/supplier-service.jar
ENTRYPOINT ["java","-jar","supplier-service.jar"]