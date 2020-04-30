package com.inti.formation.stock.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@Configuration
@EnableReactiveMongoRepositories
public class ApplicationShopStock { public static void main(String [] args){SpringApplication.run(ApplicationShopStock.class, args);} }
