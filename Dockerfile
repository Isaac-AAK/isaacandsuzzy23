FROM openjdk:17
EXPOSE 8080
COPY target/wedding-web-0.0.1.jar wedding-web-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/wedding-web-0.0.1.jar"]