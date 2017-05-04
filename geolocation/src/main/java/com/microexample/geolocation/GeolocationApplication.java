package com.microexample.geolocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

import com.amazonaws.services.dynamodbv2.*;
import com.microexample.geolocation.contracts.*;
import com.microexample.geolocation.factories.*;
import com.microexample.geolocation.repositories.*;
import com.microexample.geolocation.services.*;

@SpringBootApplication
@Configuration
public class GeolocationApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeolocationApplication.class, args);
    }

    // usealy api use "request" Scope

    @Bean(destroyMethod="dispose")
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
        IFactory<AmazonDynamoDB> dbContextFactory = new AmazonDynamoDbFactory();
        AmazonDynamoDB dbContext = dbContextFactory.create();
        return dbContext;
    }

}
