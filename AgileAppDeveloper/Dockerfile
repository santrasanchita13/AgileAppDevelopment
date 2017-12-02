FROM ubuntu:14.04 AS jarfile
VOLUME /gitjar
COPY . /gitjar/gitrepo/
WORKDIR /gitjar/gitrepo/AgileAppDeveloper
RUN sudo add-apt-repository ppa:webupd8team/java -y
RUN sudo apt-get update
RUN sudo apt-get install -y oracle-java8-set-default
RUN update-alternatives --config java
RUN ls & ./gradlew build jar && ./gradlew build bootRepackage

FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY --from=jarfile /gitjar/gitrepo/AgileAppDeveloper/build/libs/agileappdeveloper-0.0.2.jar app.jar
# ARG JAR_FILE
# ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]