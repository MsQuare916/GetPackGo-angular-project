FROM openjdk:11-jre-slim
WORKDIR usr/src
EXPOSE 8088
ADD ./target/payment-service.jar /usr/src/payment-service.jar
ENTRYPOINT ["java","-jar","payment-service.jar"]