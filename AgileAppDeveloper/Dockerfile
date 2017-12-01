FROM openjdk:8-jdk-alpine
VOLUME /tmp
#ARG JAR_FILE
ADD "https://github.com/santrasanchita13/AgileAppDevelopment/tree/master/AgileAppDeveloper/src/main/resources/agileappdeveloper.jar?raw=true" app.jar
RUN chmod +x "/app.jar"
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]