FROM adoptopenjdk/openjdk12:latest
ENV TZ=Europe/Pairs
RUN ls
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
VOLUME /data
ADD target/spring-reactive-mongoapi-stock.jar spring-reactive-mongoapi-stock.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /spring-reactive-mongoapi-stock.jar" ]