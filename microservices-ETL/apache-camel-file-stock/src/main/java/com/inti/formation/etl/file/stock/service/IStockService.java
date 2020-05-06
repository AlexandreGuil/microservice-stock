package com.inti.formation.etl.file.stock.service;


import com.inti.formation.etl.file.stock.model.mongodb.StockMongodbType;

public interface IStockService {
    public StockMongodbType save(StockMongodbType stock);
}
