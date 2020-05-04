package com.inti.formation.stock.topic.consumer.service;

import com.inti.formation.stock.topic.consumer.model.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerBuilder {


    @Autowired
    private IStockService serv;

    @Value("${kafka.topic-name}")
    private String topicName;

    @KafkaListener(topics = "${kafka.topic-name}", groupId = "${kafka.consumer-group-id}")
    public void consume(Stock stock) {

        serv.saveStock(stock);
        log.info(topicName + " message " + stock.getKey() + " : " + stock.toString() + " \nwas pushed into the index shop elasticsearch");
    }
}
