## Creer le topic de délésion



## Creer un index avec le mapping de l'entité stock dans elaticsearch

 > cURL cmd

```bash

curl -X PUT -H "Content-Type: application/json" -d @./spring-reactive-consumer-stock/src/main/resources/mapping-stock-delete-item.json  "localhost:9200/stock-delete-item?pretty"

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