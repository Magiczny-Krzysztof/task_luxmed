FROM openjdk:21
VOLUME /tmp
EXPOSE 8080
COPY target/luxmedTask-1.0-SNAPSHOT.jar /luxmedTask.jar
ENTRYPOINT ["java","-jar","/luxmedTask.jar"]