FROM openjdk:11-jre-slim
WORKDIR usr/src
EXPOSE 8085
ADD ./target/Authentication-Service.jar /usr/src/Authentication-Service.jar
ENTRYPOINT ["java","-jar","Authentication-Service.jar"]