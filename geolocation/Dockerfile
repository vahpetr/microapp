FROM frolvlad/alpine-oraclejdk8:slim
WORKDIR app
ADD target/geolocation-latest.jar geolocation.jar
EXPOSE 8080
CMD java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar geolocation.jar
