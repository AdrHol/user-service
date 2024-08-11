package com.adrhol.user_service.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.adrhol.user_service.adapters.out.persistence")
public class MongoConfiguration {
}
