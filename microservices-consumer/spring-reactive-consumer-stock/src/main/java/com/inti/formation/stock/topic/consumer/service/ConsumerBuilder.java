package com.inti.formation.stock.topic.consumer.service;

import com.inti.formation.stock.topic.consumer.data.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerBuilder {

    @Value("${kafka.topic-name}")
    private String topicName;

    @KafkaListener(topics = "${kafka.topic-name}", groupId = "${kafka.consumer-group-id}")
    public void consume(Stock stock) {log.info(topicName + " index " + stock.getKey() + " : " + stock.toString());}
}
