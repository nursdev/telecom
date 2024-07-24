FROM openjdk:21
VOLUME /app
WORKDIR /telecom
COPY ./target/telecom-v1.0.0.jar /telecom
ENTRYPOINT ["java", "-jar", "telecom-v1.0.0.jar"]