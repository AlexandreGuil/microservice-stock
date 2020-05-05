FROM openjdk:12-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
VOLUME /lib
EXPOSE 27017
EXPOSE 9092
EXPOSE 8080
ARG JAR_FILE=target/spring-reactive-mongoapi-stock.jar
ADD ${JAR_FILE} spring-reactive-mongoapi-stock.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /spring-reactive-mongoapi-stock.jar" ]