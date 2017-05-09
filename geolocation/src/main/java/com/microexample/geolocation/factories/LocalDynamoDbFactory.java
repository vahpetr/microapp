package com.microexample.geolocation.factories;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.*;
import com.microexample.geolocation.contracts.IFactory;

// example https://github.com/awslabs/aws-dynamodb-examples/blob/master/src/test/java/com/amazonaws/services/dynamodbv2/DynamoDBLocalFixture.java

/**
 * Local dynamo db factory
 */
public class LocalDynamoDbFactory implements IFactory<AmazonDynamoDB> {
    /**
     * Create an in-memory and in-process instance of DynamoDB Local that runs over HTTP
     */
    @Override
    public AmazonDynamoDB create() {
        // before need run server ServerRunner.createServerFromCommandLineArgs
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
                // we can use any region here
                new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2")).build();
        return amazonDynamoDB;
    }
}
