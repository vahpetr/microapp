package com.microexample.geolocation;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;

public class TestUtilites {

    public static CreateTableResult createTable(AmazonDynamoDB _dbContext, String tableName, String keyName) {
        List<KeySchemaElement> keys = new ArrayList<KeySchemaElement>();
        keys.add(new KeySchemaElement(keyName, KeyType.HASH));

        List<AttributeDefinition> attributes = new ArrayList<AttributeDefinition>();
        attributes.add(new AttributeDefinition(keyName, ScalarAttributeType.S));

        ProvisionedThroughput provisionedthroughput = new ProvisionedThroughput(1L, 1L);

        CreateTableRequest request = new CreateTableRequest().withTableName(tableName).withKeySchema(keys)
                .withAttributeDefinitions(attributes).withProvisionedThroughput(provisionedthroughput);

        return _dbContext.createTable(request);
    }

}
