FROM adoptopenjdk/openjdk12:latest
ENV TZ=Europe/Pairs
RUN ls
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
<<<<<<< HEAD
VOLUME /tmp
EXPOSE 8088

#Le nom du fichier jar de l'application
ARG JAR_FILE=target/spring-reactive-mongoapi-stock-1.0-RELEASE.jar

#Ajouter l'application jar au container
ADD target/spring-reactive-mongoapi-stock-1.0-RELEASE.jar spring-reactive-mongoapi-stock-1.0-RELEASE.jar

#Run l'application jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]


=======
VOLUME /data
ADD target/spring-reactive-mongoapi-stock.jar spring-reactive-mongoapi-stock.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /spring-reactive-mongoapi-stock.jar" ]
>>>>>>> master
