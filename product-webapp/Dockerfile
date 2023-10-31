FROM openjdk:11-jre-slim
WORKDIR usr/src
EXPOSE 8085
ADD ./target/product-webapp.jar /usr/src/product-webapp.jar
ENTRYPOINT ["java","-jar","product-webapp.jar"]