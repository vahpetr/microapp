# FROM java:8-jre
FROM frolvlad/alpine-oraclejdk8:slim
ADD target/geolocation-latest.jar geolocation.jar
CMD java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar geolocation.jar
