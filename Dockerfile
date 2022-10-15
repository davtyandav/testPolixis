FROM openjdk:11

ADD target/testPolixis-0.0.1-SNAPSHOT.jar testPolixis-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar" ,"testPolixis-0.0.1-SNAPSHOT.jar"]


