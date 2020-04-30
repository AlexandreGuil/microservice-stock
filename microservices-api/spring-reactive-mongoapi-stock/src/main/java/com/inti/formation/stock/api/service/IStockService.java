package com.inti.formation.stock.api.service;

import com.inti.formation.stock.api.model.Stock;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IStockService {

    public Mono<Stock> saveStock(Stock stock);

    public Mono<Stock> findStockById(final Long idStock);
    public Flux<Stock> findActiveStockUntileCreationDate(Date date);
    public Flux<Stock> findByMagasin(final String magasin);
    public Flux<Stock> findAllStock();

    public Mono<Void> deleteStockeById(final long idStock);
    public Mono<Void> deleteStock(Stock stock);

    public Mono<Stock> updateStock(Stock stock);

}