package com.inti.formation.stock.topic.consumer.repository;

import com.inti.formation.stock.topic.consumer.model.Stock;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockRepository extends ElasticsearchCrudRepository<Stock, Long> {

}
