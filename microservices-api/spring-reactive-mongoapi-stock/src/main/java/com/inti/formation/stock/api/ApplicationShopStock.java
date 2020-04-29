package com.inti.formation.stock.api;

import com.inti.formation.stock.api.model.Stock;
import com.inti.formation.stock.api.seriallizer.JsonPojoSeriallizer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Configuration
@EnableReactiveMongoRepositories
public class ApplicationShopStock { public static void main(String [] args){SpringApplication.run(ApplicationShopStock.class, args);} }
