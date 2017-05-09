package com.microexample.geolocation.repositories;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
// import com.amazonaws.services.dynamodbv2.model.BatchWriteItemRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.PutRequest;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.WriteRequest;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.microexample.geolocation.contracts.ICoordinatesRepository;
import com.microexample.geolocation.models.*;

// docs
// http://docs.aws.amazon.com/amazondynamodb/latest/gettingstartedguide/GettingStarted.Java.03.html#GettingStarted.Java.03.01

// examples
// https://github.com/gkatzioura/egkatzioura.wordpress.com/blob/master/DynamoDBTutorial/src/main/java/com/gkatzioura/dynamodb/user/UserRepository.java

/**
 * Coordinates repository
 */
@Repository
public class CoordinatesRepository implements ICoordinatesRepository {

    /**
     * Amazon dynamo db table name
     */
    private final String tableName = "Coordinates";

    /**
     * Amazon dynamo db context
     */
    private final AmazonDynamoDB _dbContext;

    /**
     * Coordinates repository constructor
     * @param Amazon dynamo db context
     */
    @Autowired
    public CoordinatesRepository(AmazonDynamoDB dbContext) {
        _dbContext = dbContext;
        createTableIfNotExists(_dbContext, tableName);
    }

    /**
     * Save coordinate
     * @param Coordinates array
     */
    public void save(List<Coordinate> coordinates) {
        List<WriteRequest> writeRequests = new ArrayList<>();
        Map<String, List<WriteRequest>> requestItems = new HashMap<String, List<WriteRequest>>() {
            {
                put(tableName, writeRequests);
            }
        };
        // gain more control
        // BatchWriteItemRequest batchWriteRequest = new BatchWriteItemRequest(requestItems);

        for (Coordinate coordinate : coordinates) {
            Map<String, AttributeValue> data = new HashMap<String, AttributeValue>() {
                {
                    put("deviceId", new AttributeValue().withN(Integer.toString(coordinate.deviceId)));
                    put("timestamp", new AttributeValue().withN(Long.toString(coordinate.timestamp)));
                    put("latitude", new AttributeValue().withN(Float.toString(coordinate.latitude)));
                    put("longitude", new AttributeValue().withN(Float.toString(coordinate.longitude)));
                }
            };
            PutRequest putRequest = new PutRequest(data);
            WriteRequest writeRequest = new WriteRequest(putRequest);
            writeRequests.add(writeRequest);
        }

        // _dbContext.batchWriteItem(batchWriteRequest);
        _dbContext.batchWriteItem(requestItems);
    }

    // http://docs.aws.amazon.com/amazondynamodb/latest/APIReference/API_CreateTable.html#DDB-CreateTable-request-AttributeDefinitions

    /**
     * Create Amazon dynamo db table if table not exists
     * @param Amazon dynamo db context
     * @param Amazon dynamo db table name
     */
    private void createTableIfNotExists(AmazonDynamoDB dbContext, String tableName) {
        try {
            ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput(10L, 10000L);
            List<KeySchemaElement> key = new ArrayList<KeySchemaElement>();
            key.add(new KeySchemaElement().withKeyType(KeyType.HASH).withAttributeName("deviceId"));
            key.add(new KeySchemaElement().withKeyType(KeyType.RANGE).withAttributeName("timestamp"));

            List<AttributeDefinition> definitions = new ArrayList<>();
            definitions.add(
                    new AttributeDefinition().withAttributeName("deviceId").withAttributeType(ScalarAttributeType.N));
            definitions.add(
                    new AttributeDefinition().withAttributeName("timestamp").withAttributeType(ScalarAttributeType.N));

            CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName).withKeySchema(key)
                    .withAttributeDefinitions(definitions).withProvisionedThroughput(provisionedThroughput);

            TableUtils.createTableIfNotExists(_dbContext, createTableRequest);
            TableUtils.waitUntilActive(_dbContext, tableName);
        } catch (Exception exception) {
            // do nothing if table has been created
        }
    }

}
