FROM ubuntu:14.04 AS jarfile
VOLUME /gitjar
WORKDIR /gitjar/gitrepo
#RUN sudo apt-get update
#RUN sudo apt-get install -y git
#RUN git clone git://github.com/santrasanchita13/AgileAppDevelopment.git && ls
ADD https://github.com/santrasanchita13/AgileAppDevelopment /gitjar/gitrepo
RUN ls
WORKDIR AgileAppDeveloper
RUN ./gradlew build jar && ./gradlew build bootRepackage


FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY --from=jarfile /gitjar/gitrepo/AgileAppDevelopment/AgileAppDeveloper/build/libs/agileappdeveloper-0.0.2.jar app.jar
# ARG JAR_FILE
# ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]