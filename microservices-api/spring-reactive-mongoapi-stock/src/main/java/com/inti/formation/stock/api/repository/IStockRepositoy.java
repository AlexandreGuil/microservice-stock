package com.inti.formation.stock.api.repository;

import com.inti.formation.stock.api.model.Stock;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IStockRepositoy extends ReactiveMongoRepository<Stock, Long> {

    @Query("{'_id': ?0}")
    public Mono<Stock> findByIdStock(final Long idStock);

    @Query("{'active':true}")
    public Flux<Stock> findByActiveStock();

    public Flux<Stock> findByMagasin(final String magasin);

}