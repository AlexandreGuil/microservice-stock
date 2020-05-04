# Creer un topic pour le suivit des délétion de stock

 > commande kafka

```bash

$ kafka-topics --create --topic  deleted-stock-v1 --zookeeper zookeeper:2181 --config cleanup.policy=delete  --config  delete.retention.ms=604800000 --config  retention.ms=604800000 --partitions 3 --replication-factor 1

````

# Vérifier la bonne éxécution du programme

## Entrer dans le contenaire boker

 > commande Docker

````bash

$ docker exec -it broker bash

````

## Lister le topic kaka

> commande kafka

````bash

$ kafka-topics --zookeeper zookeeper:2181 --list topics

````

## Utiliser le consumer kafka pour vérifier les push de donnée depuis l'api

> commande kafka

````bash

$ kafka-console-consumer --bootstrap-server broker:9092 --topic stock-delete-v1 --from-beginning

````

# Vérifier la bonne éxécution du programme

## Entrer dans le contenaire boker

 > commande Docker

````bash

$ docker exec -it broker bash

````

## Lister le topic kaka

> commande kafka

````bash

$ kafka-topics --zookeeper zookeeper:2181 --list topics

````

## Utiliser le consumer kafka pour vérifier les push de donnée depuis l'api

> commande kafka

````bash

$ kafka-console-consumer --bootstrap-server broker:9092 --topic stock-delete-v1 --from-beginning

````

## Crée l'image docker a partir du spring-reactive-mongoapi-stock-1.0-RELEASE.jar

````bash

$ docker build -t microservice-stock-api .

````