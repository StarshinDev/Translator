FROM maven:3.9.0-ibmjava AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM openjdk:8-jdk-alpine
COPY --from=build /home/app/target/translatorAPI-0.0.1-SNAPSHOT.jar translatorAPI.jar
ENTRYPOINT ["java","-jar", "translatorAPI.jar"]