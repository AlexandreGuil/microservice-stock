package com.inti.formation.stock.topic.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories
public class AppConsumer {

    public static void main(String[] args) {
        SpringApplication.run(AppConsumer.class, args);
    }
}
