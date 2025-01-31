FROM eclipse-temurin:22-jdk AS buildstage

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY pom.xml .
COPY src /app/src

RUN mvn clean package

FROM eclipse-temurin:22-jdk

COPY --from=buildstage /app/target/healtheye_rbmq2-0.0.1-SNAPSHOT.jar /app/healtheye_rbmq2.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "/app/healtheye_rbmq2.jar"]
