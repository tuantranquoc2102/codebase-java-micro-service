package com.microservice.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableMongoRepositories
public class MongoDbServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MongoDbServiceApplication.class, args);
    }
}