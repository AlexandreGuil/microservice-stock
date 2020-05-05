FROM openjdk:12-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
VOLUME /lib
ARG JAR_FILE=target/spring-reactive-mongoapi-stock.jar
ADD ${JAR_FILE} spring-reactive-mongoapi-stock.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar","/spring-reactive-mongoapi-stock.jar"]