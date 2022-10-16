FROM openjdk:11
WORKDIR /user/app/
ADD target/testPolixis-0.0.1-SNAPSHOT.jar testPolixis-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar" ,"testPolixis-0.0.1-SNAPSHOT.jar"]


