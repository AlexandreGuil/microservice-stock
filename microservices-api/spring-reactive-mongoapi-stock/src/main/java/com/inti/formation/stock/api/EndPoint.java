package com.inti.formation.stock.api;

import com.inti.formation.stock.api.model.Stock;
import com.inti.formation.stock.api.model.StockDel;
import com.inti.formation.stock.api.repository.StockDelRopository;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.status;

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
    private KafkaTemplate<String, StockDel> kafkaThemplate;

    @Autowired(required = true)
    IStockService serv;

    @Autowired
    StockDelRopository stockDelRopository;

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
                stock.getActive(), stock.getCreationDate(), stock.getMagasin())) {
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
                .doOnNext(data -> log.info(data.getIdStock() + " is found"));
    }

    @GetMapping
    @RequestMapping(value = "/findStockByActiveAndDate")
    public Flux<Stock> findActiveStockUntileDate(
            @RequestParam(name = "date")
                    String date) throws ParseException {
        log.debug(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        return serv.findActiveStockUntileCreationDate(format.parse(date))
                .doOnNext(data -> log.info(data.getCreationDate() + " is found"));
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
                stock.getCreationDate(),
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
    @ResponseStatus(code = HttpStatus.OK, reason = "This stock wase deleted")
    public Mono<Void> deleteStockeById(@RequestParam(value = "idStock")
                                               String idStock) {
        serv.findStockById(Long.parseLong(idStock)).map((Stock stock) -> {
            log.info(stockDelRopository.getStockDelByStock(stock).toString());
            StockDel message = stockDelRopository.getStockDelByStock(stock);
            ProducerRecord<String, StockDel> producerRecord = new ProducerRecord<>(topicName, message.getKey(),
                    message);
            kafkaThemplate.send(producerRecord);
            return Mono.just(stock);
        }).subscribe();
        log.info("The stock entity with '_id': " + idStock + " was send in the delation topic" );
        return serv.deleteStockeById(Long.parseLong(idStock));
    }

    // Je sais pas si c'est util de garder t'a méthode @Dustnight ?
    @DeleteMapping(value = "/deleteStock", headers = "Accept=application/json; charset=utf-8")
    @ResponseStatus(value = HttpStatus.OK, reason = "This stock wase deleted")
    public Mono<Void> deleteStock(@RequestBody Stock stock){
        return serv.deleteStock(stock);
    }

}
