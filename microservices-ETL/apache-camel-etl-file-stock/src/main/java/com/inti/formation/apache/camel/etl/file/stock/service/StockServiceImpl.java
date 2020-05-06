package com.inti.formation.apache.camel.etl.file.stock.service;

import com.inti.formation.apache.camel.etl.file.stock.model.mongodb.StockMongodbType;
import com.inti.formation.apache.camel.etl.file.stock.repository.IStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockServiceImpl implements IStockService {

    @Autowired
    private IStockRepository repo;

    @Override
    public StockMongodbType save(StockMongodbType stock) { return repo.save(stock); }
}
