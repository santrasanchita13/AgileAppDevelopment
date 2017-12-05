#FROM ubuntu:14.04 AS jarfile
#VOLUME /gitjar
#COPY . /gitjar/gitrepo/
#WORKDIR /gitjar/gitrepo/AgileAppDeveloper
#RUN sudo apt-get update
#RUN sudo apt-get install -y software-properties-common
#RUN sudo add-apt-repository ppa:webupd8team/java -y
#RUN sudo apt-get update
#RUN echo debconf shared/accepted-oracle-license-v1-1 select true | \
#sudo debconf-set-selections
#RUN echo debconf shared/accepted-oracle-license-v1-1 seen true | \
#sudo debconf-set-selections
#RUN sudo apt-get install -y oracle-java8-set-default
#ENV GOOGLE_APPLICATION_CREDENTIALS /gitjar/gitrepo/AgileAppDeveloper/src/main/resources/AgileAppLogin-beb6fc34e914.json
#RUN ./gradlew build --info jar && ./gradlew build --info bootRepackage
#RUN ls /gitjar/gitrepo/AgileAppDeveloper/build/libs


FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY . /tmp/gitrepo
#ADD /tmp/gitrepo/src/main/resources/agileappdeveloper-0.0.2.jar app.jar
ENV GOOGLE_APPLICATION_CREDENTIALS /tmp/gitrepo/AgileAppDeveloper/src/main/resources/AgileAppLogin-beb6fc34e914.json
#COPY --from=jarfile /gitjar/gitrepo/AgileAppDeveloper/build/libs/agileappdeveloper-0.0.2.jar app.jar
#ARG JAR_FILE
#ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/tmp/gitrepo/src/main/resources/agileappdeveloper.jar"]