package com.inti.formation.apache.camel.etl.file.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@SpringBootApplication
@EnableMongoRepositories
public class EtlFileSockRunner {
    public static void main(String[] args) { SpringApplication.run(EtlFileSockRunner.class, args); }
}
