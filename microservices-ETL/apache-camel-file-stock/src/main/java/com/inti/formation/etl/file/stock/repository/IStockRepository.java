package com.inti.formation.etl.file.stock.repository;


import com.inti.formation.etl.file.stock.model.mongodb.StockMongodbType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockRepository extends MongoRepository<StockMongodbType, Long> {}
