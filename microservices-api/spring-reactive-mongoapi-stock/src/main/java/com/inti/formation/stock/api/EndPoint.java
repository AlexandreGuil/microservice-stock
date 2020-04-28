package com.inti.formation.stock.api;

import com.inti.formation.stock.api.model.Stock;
import com.inti.formation.stock.api.rest.exception.InternalServerException;
import com.inti.formation.stock.api.rest.exception.StockModelNotFindInApiStockMongoDbCollectionException;
import com.inti.formation.stock.api.rest.exception.StockParamException;
import com.inti.formation.stock.api.service.IStockService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;


import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.websocket.server.PathParam;
import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.web.servlet.function.EntityResponse.fromObject;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/stock")
public class EndPoint {

    @Value("${kafka.topic-name}")
    private String topicName;

    @Value("${kafka.compression-type}")
    private String compressionType;

    @Autowired
    private KafkaTemplate<String, Stock> kafkaThemplate;

    @Autowired(required = true)
    IStockService serv;

    @ExceptionHandler(StockParamException.class)
    public Mono<ResponseEntity<String>> handlerStockParamException(StockParamException err) {
        return Mono.just(badRequest().body("Missing parameter when you submit a stock entity" + err.getMessage()));
    }

    @ExceptionHandler(StockModelNotFindInApiStockMongoDbCollectionException.class)
    public Mono<ResponseEntity<String>> handlerStockModelNotFindInApiStockMongoDbCollectionException(
            StockModelNotFindInApiStockMongoDbCollectionException err) {
        return Mono.just(badRequest().body("No such entity in the mongodb collection" + err.getMessage()));
    }

    @ExceptionHandler(InternalServerException.class)
    public Mono<ResponseEntity<String>> handlerInternalServerException() {
        return Mono.just(status(HttpStatus.INTERNAL_SERVER_ERROR).body("The server encontred an internal error during Queries execution"));
    }

    @PostMapping(value = "/saveStock",
            headers = "Accept=application/json; charset=utf-8")
    @ResponseStatus(value = HttpStatus.CREATED,
            reason = "Stock is save in the database")
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

    @GetMapping
    @RequestMapping(value = "/findStockById")
    public Mono<Stock> findStockById(
            @RequestParam(value = "idStock")
                    String idStock) {
        log.debug("input _id", idStock);
        return serv.findStockById(Long.parseLong(idStock))
                .doOnNext(data -> log.info(data.getIdStock() + "is found"));
    }

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

    @GetMapping
    @RequestMapping(value = "/stocks")
    public Flux<Stock> findAllStock() {
        log.info("All stocks searching");
        return serv.findAllStock()
                .switchIfEmpty(Flux.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map(data -> data);
    }

    @PutMapping(value = "/updateStock", headers = "Accept=application/json; charset=utf-8")
    @ResponseStatus(value =  HttpStatus.OK, reason = "Stock document is up to date")
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

    /**
     *
     * ProducerRecord une class générique < le Type (primitif, ou objet) de cléf de la donnée à envoyé dans le topic,
     *                                      Le Type de l'entité à envoyé dans le topic >
     *
     * ProducerRecord<String, Stock> producerRecord = new ProducerRecord<>(topicName,
     *                 stock.getIdStock().toString(), stock); // UUID : value unique
     *         kafkaThemplate.send(producerRecord);
     *
     */

    @DeleteMapping(value = "/deleteStockeById", headers = "Accept=application/json; charset=utf-8")
    @ResponseStatus(value = HttpStatus.OK, reason = "This stock wase deleted")
    public Mono<Void> deleteStockeById(@RequestParam(value = "idStock")
                                       String idStock) {
        serv.findStockById(Long.parseLong(idStock)).map((Stock stock) -> {
            ProducerRecord<String, Stock> producerRecord = new ProducerRecord<>(topicName,
                    UUID.randomUUID().toString(), stock);
            kafkaThemplate.send(producerRecord);
            return Mono.just(stock);
        }).subscribe();
        log.info("The stock entity with '_id': " + idStock + " was send in the delation topic" );
        return serv.deleteStockeById(Long.parseLong(idStock));
    }

    @DeleteMapping(value = "/deleteStock", headers = "Accept=application/json; charset=utf-8")
    @ResponseStatus(value = HttpStatus.OK, reason = "This stock wase deleted")
    public Mono<Void> deleteStock(@RequestBody Stock stock){
        return serv.deleteStock(stock);
    }

}
