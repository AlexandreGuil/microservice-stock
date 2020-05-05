FROM openjdk:12-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
VOLUME /lib
ARG JAR_FILE=target/spring-reactive-consumer-stock.jar
ADD ${JAR_FILE} spring-reactive-consumer-stock.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar","/spring-reactive-consumer-stock.jar"]