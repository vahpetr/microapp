package com.microexample.geolocation.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.amazonaws.services.dynamodbv2.util.TableUtils.TableNeverTransitionedToStateException;
import com.microexample.geolocation.contracts.ICoordinatesRepository;
import com.microexample.geolocation.models.*;

// https://github.com/awslabs/aws-dynamodb-examples/blob/master/src/test/java/com/amazonaws/services/dynamodbv2/local/embedded/DynamoDBEmbeddedTest.java
// https://github.com/awslabs/aws-dynamodb-examples/blob/master/src/test/java/com/amazonaws/services/dynamodbv2/DynamoDBLocalFixture.java

@Repository
public class CoordinatesRepository implements ICoordinatesRepository {

    private final AmazonDynamoDB _dbContext;

    @Autowired
    public CoordinatesRepository(AmazonDynamoDB dbContext) {
        _dbContext = dbContext;
    }

    public void save(Coordinate coordinate) {

    }

    private void createTableIfNotExists() throws InterruptedException, TableNeverTransitionedToStateException {
        String tableName = "coordinates";

        CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName);

        // TODO add table shema

        TableUtils.createTableIfNotExists(_dbContext, createTableRequest);
        TableUtils.waitUntilActive(_dbContext, tableName);
    }
}
