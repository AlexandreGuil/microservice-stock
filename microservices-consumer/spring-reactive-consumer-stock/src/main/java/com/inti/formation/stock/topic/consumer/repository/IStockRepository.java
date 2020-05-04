package com.inti.formation.stock.topic.consumer.repository;

import com.inti.formation.stock.topic.consumer.model.StockEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockRepository extends ElasticsearchCrudRepository<StockEs, Long> {

}
