## Start with a base image containing Java runtime
#FROM openjdk:8-jdk-alpine
#
## Add Maintainer Info
#LABEL maintainer="kerkomikhail@gmail.com"
#
## Add a volume pointing to /tmp
#VOLUME /tmp
#
## Make port 8080 available to the world outside this container
#EXPOSE 8080
#
## The application's jar file
#ARG JAR_FILE=target/Store-0.0.1-SNAPSHOT.jar
#
## Add the application's jar to the container
#ADD ${JAR_FILE} Store.jar
#
## Run the jar file
#ENTRYPOINT ["java","-jar","/Store.jar"]

FROM maven as build
ARG LOCAL_ENV=false
RUN ls
COPY . /app/
WORKDIR /app
# Run the following only if we are not in Dev Environment:
RUN mvn clean install
FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/Store-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT ["java","-jar","Store-0.0.1-SNAPSHOT.jar"]
#COPY --from=build app/target/Store-0.0.1-SNAPSHOT.jar /usr/local/tomcat/webapps