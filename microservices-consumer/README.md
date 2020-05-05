## Creer le topic de délésion



## Creer un index avec le mapping de l'entité stock dans elaticsearch

 > cURL cmd

```bash

curl -X PUT -H "Content-Type: application/json" -d @./spring-reactive-consumer-stock/src/main/resources/mapping-stock-delete-item.json  "localhost:9200/shop?pretty"

```

 > JSON document body

```json

{
    "mappings": {
        "properties": {
            "key": {"type": "keyword"},
            "idStock": {"type": "long"},
            "quantite": {"type": "long"},
            "magasin": {"type": "keyword"},
            "active": {"type": "boolean"},
            "idProduit": {"type": "long"},
            "date": {"type": "date"}
        }
    }
}

```

## Crée l'image docker a partir du spring-reactive-mongoapi-stock.jar

> commande Docker

````bash

$ docker build -t microservice-stock-consumer .

````

## Crée un contenair a partir de l'image microservice-stock-consumer

> commande Docker

````bash

$ docker run -p 8082:8082 -t micrservice-consumre-stock

````

## Lien Url pour consulter le dashboard kibana

````http request

http://localhost:5601/goto/587ca412cfbec33b181c8b1381a267c8 (snapshot)
http://localhost:5601/app/kibana#/dashboard/c071f650-8ecf-11ea-9be5-6f46128ac336?_g=(filters%3A!()%2CrefreshInterval%3A(pause%3A!t%2Cvalue%3A0)%2Ctime%3A(from%3Anow-15m%2Cto%3Anow)) (saved object)

````