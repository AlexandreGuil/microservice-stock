package com.inti.formation.stock.topic.consumer.service;

import com.inti.formation.stock.topic.consumer.model.StockEs;
import com.inti.formation.stock.topic.consumer.model.StockTopic;
import com.inti.formation.stock.topic.consumer.repository.StockEsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@Slf4j
public class ConsumerBuilder {


    @Autowired
    private IStockService serv;

    @Value("${kafka.topic-name}")
    private String topicName;

    @KafkaListener(topics = "${kafka.topic-name}", groupId = "${kafka.consumer-group-id}")
    public void consume(StockTopic stock) throws ParseException {
//        log.info(topicName + " message " + stock.getKey() + " : " + stock.toString());
        StockEs persist = serv.saveStock(StockEsRepository.cloneStockTopic(stock));
        log.info(topicName + " message " + stock.getKey() + " : " + persist.toString() + " \nwas pushed into the index shop elasticsearch");
    }
}
