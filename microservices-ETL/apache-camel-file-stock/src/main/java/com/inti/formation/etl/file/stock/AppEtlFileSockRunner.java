package com.inti.formation.etl.file.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@SpringBootApplication
@EnableMongoRepositories
public class AppEtlFileSockRunner {
    public static void main(String[] args) {
        SpringApplication.run(AppEtlFileSockRunner.class, args);
    }
}
