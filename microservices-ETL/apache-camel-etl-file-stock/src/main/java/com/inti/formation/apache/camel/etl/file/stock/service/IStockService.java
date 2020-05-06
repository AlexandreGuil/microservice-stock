package com.inti.formation.apache.camel.etl.file.stock.service;

import com.inti.formation.apache.camel.etl.file.stock.model.mongodb.StockMongodbType;

public interface IStockService {
    public StockMongodbType save(StockMongodbType stock);
}
