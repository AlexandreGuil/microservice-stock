package com.inti.formation.stock.api.service;

import com.inti.formation.stock.api.model.Stock;
import com.inti.formation.stock.api.repository.IStockRepositoy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Slf4j
@Component
public class StockServiceImpl implements IStockService {

    @Autowired
    private IStockRepositoy repo;

    @Override
    public Mono<Stock> saveStock(Stock stock) {
        return repo.save(stock);
    }

    @Override
    public Mono<Stock> findStockById(final Long idStock) {return repo.findByIdStock(idStock);}

    @Override
    public Flux<Stock> findActiveStockUntileCreationDate(Date date) { return repo.findActiveStockUntileCreationDate(date); }

    @Override
    public Flux<Stock> findByMagasin(String magasin) {
        return repo.findByMagasin(magasin);
    }

    @Override
    public Flux<Stock> findAllStock() { return repo.findAll(); }

    @Override
    public Mono<Void> deleteStockeById(final long idStock) {return repo.deleteById(idStock);}

    @Override
    public Mono<Void> deleteStock(Stock stock) {return repo.delete(stock);}

    @Override
    public Mono<Stock> updateStock(Stock stock) {return repo.save(stock);}

}
