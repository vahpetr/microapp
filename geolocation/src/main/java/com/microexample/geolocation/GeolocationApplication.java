package com.microexample.geolocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.microexample.geolocation.contracts.ICoordinatesRepository;
import com.microexample.geolocation.contracts.ICoordinatesService;
import com.microexample.geolocation.contracts.IFactory;
import com.microexample.geolocation.factories.InMemoryDynamoDbFactory;
import com.microexample.geolocation.repositories.CoordinatesRepository;
import com.microexample.geolocation.services.CoordinatesService;

@SpringBootApplication
@Configuration
public class GeolocationApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeolocationApplication.class, args);
    }

    // usealy api use "request" Scope

    @Bean
    @Scope("singleton")
    public ICoordinatesService coordinatesService() {
        ICoordinatesRepository coordinatesRepository = coordinatesRepository();
        return new CoordinatesService(coordinatesRepository);
    }

    @Bean
    @Scope("singleton")
    public ICoordinatesRepository coordinatesRepository() {
        AmazonDynamoDB dbContext = amazonDynamoDB();
        return new CoordinatesRepository(dbContext);
    }

    @Bean(destroyMethod="shutdown")
    @Scope("singleton")
    public AmazonDynamoDB amazonDynamoDB() {
        IFactory<AmazonDynamoDB> dbContextFactory = dbContextFactory();
        AmazonDynamoDB dbContext = dbContextFactory.create();
        return dbContext;
    }

    @Bean
    @Scope("singleton")
    public IFactory<AmazonDynamoDB> dbContextFactory() {
        // IFactory<AmazonDynamoDB> dbContextFactory = new DynamoDbFactory();
        IFactory<AmazonDynamoDB> dbContextFactory = new InMemoryDynamoDbFactory();
        return dbContextFactory;
    }
}
