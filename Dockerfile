FROM openjdk:8
ADD target/s3-study.jar  s3-study.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "s3-study.jar"]