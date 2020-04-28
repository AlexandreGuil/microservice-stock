## spring reactive mongoapi stock

> Entrer une valeur stock dans la collection 

Requette Http:

```http request
localhost:8080/v1/stock/saveStock
```

Méthode dans le Endpoint.java :

```java
public class EndPoint {
    @PostMapping(value = "/saveStock", headers = "Accept=application/json; charset=utf-8")  
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Stock is save in the database")
    public Mono<String> saveStock(@RequestBody Stock stock) {  
        if(ObjectUtils.anyNotNull(stock) && !ObjectUtils.allNotNull(stock.getQuantite(),  
            stock.getActive(), stock.getDate(), stock.getMagasin())) {  
            log.error("Validation error: one of the parameters was not store in the stock instance");  
            return Mono.error(new StockParamException("Stock params exception"));  
        }  
        return Mono.just(stock)  
        .map(input -> {  
            return serv.saveStock(input).subscribe().toString();  
            });
    }
}
```

> Chercher toutes les valeurs de stocks dans la callection 

Requette Http:

```http request
localhost:8080/v1/stock/stocks
```

Méthode dans le Endpoint.java :

```java
public class EndPoint {
    @GetMapping  
    @RequestMapping(value = "/stocks")  
    public Flux<Stock> findAllStock() {  
        log.info("All stocks searching");  
        return serv.findAllStock()  
        .switchIfEmpty(Flux.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))  
        .map(data -> data);  
    }
}
```

> Chercher des les stocks par magasin

Requette Http:

```http request
localhost:8080/v1/stock/findByMagasin?magasin=MAG1
```

Méthode dans le Endpoint.java :

```java
public class EndPoint {
    @GetMapping  
    @RequestMapping(value = "/findByMagasin")  
    public Flux<Stock> findByMagasin(  
        @RequestParam(name = "magasin")  
        String magasin  
        ) {  
        log.debug("input magasin", magasin);  
        return serv.findByMagasin(magasin)  
        .doOnNext(data -> log.info(data.getMagasin() + " is found"));  
    }
}
```

> Chercher les stock actifs à partir d'une date donnée

Requette Http:

```http request
localhost:8080/v1/stock/findStockByActivAndDate?date=2020-05-08T00:00:00.001Z
```

Méthode dans le Endpoint.java :

```java
public class EndPoint {
    @GetMapping  
    @RequestMapping(value = "/findStockByActivAndDate")  
    public Flux<Stock> findActiveStockUntileDate(  
        @RequestParam(name = "date")  
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  
        Date date) {  
        log.debug("inpute date", date);  
        return serv.findActiveStockUntileDate(date)  
           .doOnNext(data -> log.info(data.getDate() + " is found"));  
    }
}
```

> Modifier les valeurs de champs dans la collection stock

Requette Http:

```http request
localhost:8080/v1/stock/updateStock
```

Méthode dans le Endpoint.java :

```java
public class EndPoint {
    @PutMapping(value = "/updateStock", headers = "Accept=application/json; charset=utf-8")  
    @ResponseStatus(value = HttpStatus.OK, reason = "Stock document is up to date")  
    public Mono<String> updateStock(@RequestBody Stock stock){  
        if(ObjectUtils.anyNotNull(stock) && !ObjectUtils.anyNotNull(stock.getQuantite(),  
            stock.getMagasin(),  
            stock.getDate(),  
            stock.getActive(),  
            stock.getIdStock())) {
            log.error("Update error: the input values of stocks is not valide");  
            return Mono.error(new StockParamException("Stock params exception"));  
        }  
        return Mono.just(stock)  
            .map(consumer -> serv.updateStock(consumer).subscribe().toString());  
    }
}
```

> Suprimer un champ dans la collection Stock

Requette Http:

```http request
localhost:8080/v1/stock/deleteStock
```

Méthode dans le Endpoint.java :

```java
public class EndPoint {
    @DeleteMapping
    @RequestMapping(value ="/deleteStock")
    public Mono<Void> deleteStock(@RequestBody Stock stock){
		return serv.deleteStock(stock);   	
    }
}
```