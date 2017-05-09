package com.microexample.geolocation.factories;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.microexample.geolocation.contracts.IFactory;
import com.amazonaws.services.dynamodbv2.local.embedded.DynamoDBEmbedded;

// maven install
// http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.html

// maven install more info
// http://stackoverflow.com/questions/26901613/easier-dynamodb-local-testing

// bash install
// https://gist.github.com/debnath/9ac1e1548a4cc7c6041d4ed744e3a5dd

// examples
// https://github.com/awslabs/aws-dynamodb-examples/blob/master/src/test/java/com/amazonaws/services/dynamodbv2/DynamoDBLocalFixture.java
// https://github.com/awslabs/aws-dynamodb-examples/blob/master/src/test/java/com/amazonaws/services/dynamodbv2/local/embedded/DynamoDBEmbeddedTest.java

/**
 * In memody dunamo db factory
 */
public class InMemoryDynamoDbFactory implements IFactory<AmazonDynamoDB> {
    /**
     * Create an in-memory and in-process instance of DynamoDB Local that skips HTTP
     * @param Amazon db context
     */
    @Override
    public AmazonDynamoDB create() {
        System.setProperty("sqlite4java.library.path", "target/dependencies");
        AmazonDynamoDB amazonDynamoDB = DynamoDBEmbedded.create().amazonDynamoDB();
        return amazonDynamoDB;
    }
}
