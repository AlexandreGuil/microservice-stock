package com.inti.formation.stock.topic.consumer.service;

import com.inti.formation.stock.topic.consumer.model.StockEs;
import com.inti.formation.stock.topic.consumer.repository.IStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements IStockService {

    @Autowired
    IStockRepository repo;

    @Override
    public StockEs saveStock(StockEs stock) {
        return repo.save(stock);
    }
}
