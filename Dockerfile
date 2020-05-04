#Commencer avec une image de base contenant Java runtime

FROM openjdk:8-jdk-alpine

#Ajouter les infos de l'éditeur 

LABEL maintainer="mangaspowerful@gmail.com"

#Ajouter un volume pointant vers /tmp 
VOLUME /tmp

#Créer un port 8080 disponible les utilisateurs extérieurs au container
EXPOSE 8080

#Le nom du fichier jar de l'application
ARG JAR_FILE=target/spring-reactive-mongoapi-stock-1.0-SNAPSHOT.jar

#Ajouter l'application jar au container
ADD ${JAR_FILE} spring-reactive-mongoapi-stock

#Run l'application jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
