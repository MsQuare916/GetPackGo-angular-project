FROM openjdk:11-jre-slim
WORKDIR usr/src
EXPOSE 9000
ADD ./target/booking-service.jar /usr/src/booking-service.jar
ENTRYPOINT ["java","-jar","booking-service.jar"]
