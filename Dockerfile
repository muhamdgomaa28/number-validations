FROM maslick/minimalka:jdk11
ADD /target/JumiaTask-0.0.1-SNAPSHOT.jar JumiaTask-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","JumiaTask-0.0.1-SNAPSHOT.jar"]