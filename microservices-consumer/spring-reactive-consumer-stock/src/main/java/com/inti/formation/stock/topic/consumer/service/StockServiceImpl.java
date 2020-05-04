package com.inti.formation.stock.topic.consumer.service;

import com.inti.formation.stock.topic.consumer.model.Stock;
import com.inti.formation.stock.topic.consumer.repository.IStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements IStockService {

    @Autowired
    IStockRepository repo;

    @Override
    public void saveStock(Stock stock) {
        repo.save(stock);
    }
}
