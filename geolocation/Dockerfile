 # frolvlad/alpine-oraclejdk8:slim
FROM java:8-jre
ADD target/geolocation-latest.jar geolocation.jar
# VOLUME /tmp

# RUN bash -c 'touch /geolocation.jar'
CMD java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar geolocation.jar
# ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar /geolocation.jar
# ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/geolocation.jar"]
# CMD java $JAVA_OPTS -Xmx200m -Djava.security.egd=file:/dev/./urandom -jar geolocation.jar
# EXPOSE 7777
