package com.microexample.geolocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
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

    @Bean(destroyMethod = "dispose")
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

    @Bean(destroyMethod = "shutdown")
    @Scope("singleton")
    public AmazonDynamoDB amazonDynamoDB() {
        IFactory<AmazonDynamoDB> dbContextFactory = dynamoDbFactory();
        AmazonDynamoDB dbContext = dbContextFactory.create();
        return dbContext;
    }

    @Bean
    @Scope("singleton")
    public IFactory<AmazonDynamoDB> dynamoDbFactory() {
        AWSCredentialsProvider awsCredentialsProvider = AWSCredentialsProvider();
        IFactory<AmazonDynamoDB> dbContextFactory = new AmazonDynamoDbFactory(awsCredentialsProvider);
        return dbContextFactory;
    }

    @Bean
    @Scope("singleton")
    public AWSCredentialsProvider AWSCredentialsProvider() {
        EnvironmentVariableCredentialsProvider awsCredentialsProvider = new EnvironmentVariableCredentialsProvider();
        return awsCredentialsProvider;
    }

}
