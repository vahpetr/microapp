/*
* Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License").
* You may not use this file except in compliance with the License.
* A copy of the License is located at
*
*  http://aws.amazon.com/apache2.0
*
* or in the "license" file accompanying this file. This file is distributed
* on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
* express or implied. See the License for the specific language governing
* permissions and limitations under the License.
*/
package com.microexample.geolocation.factories;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.amazonaws.services.dynamodbv2.util.TableUtils.TableNeverTransitionedToStateException;

public class AmazonDynamoDbFactoryTests {


    @Test(timeout = 1000 * 60)
    public void createTableTest() {
        String tableName = "Movies";
        AmazonDynamoDB ddb = null;
        try {
            ddb = new AmazonDynamoDbFactory().create();

            DeleteTableRequest deleteTableRequest = new DeleteTableRequest(tableName);
            boolean deleting = TableUtils.deleteTableIfExists(ddb, deleteTableRequest);

            if(deleting) return;

            String hashKeyName = "film_id";
            CreateTableResult res = createTable(ddb, tableName, hashKeyName);

            TableDescription tableDesc = res.getTableDescription();
            assertEquals(tableName, tableDesc.getTableName());
            assertEquals("[{AttributeName: " + hashKeyName + ",KeyType: HASH}]", tableDesc.getKeySchema().toString());
            assertEquals("[{AttributeName: " + hashKeyName + ",AttributeType: S}]",
                    tableDesc.getAttributeDefinitions().toString());
            assertEquals(Long.valueOf(1L), tableDesc.getProvisionedThroughput().getReadCapacityUnits());
            assertEquals(Long.valueOf(1L), tableDesc.getProvisionedThroughput().getWriteCapacityUnits());
            assertEquals("ACTIVE", tableDesc.getTableStatus());
            assertEquals("arn:aws:dynamodb:ddblocal:000000000000:table/Movies", tableDesc.getTableArn());

            ListTablesResult tables = ddb.listTables();
            assertEquals(1, tables.getTableNames().size());

            try {
                TableUtils.waitUntilExists(ddb, tableName);
            } catch (InterruptedException ex) {
                Assert.fail(ex.getMessage());
            } catch (TableNeverTransitionedToStateException ex) {
                Assert.fail(ex.getMessage());
            }
        } finally {
            ddb.shutdown();
        }
    }

    private static CreateTableResult createTable(AmazonDynamoDB ddb, String tableName, String hashKeyName) {
        List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
        attributeDefinitions.add(new AttributeDefinition(hashKeyName, ScalarAttributeType.S));

        List<KeySchemaElement> ks = new ArrayList<KeySchemaElement>();
        ks.add(new KeySchemaElement(hashKeyName, KeyType.HASH));

        ProvisionedThroughput provisionedthroughput = new ProvisionedThroughput(1L, 1L);

        CreateTableRequest request = new CreateTableRequest().withTableName(tableName)
                .withAttributeDefinitions(attributeDefinitions).withKeySchema(ks)
                .withProvisionedThroughput(provisionedthroughput);

        CreateTableResult result = null;

        try {
            result = ddb.createTable(request);
        } catch (final ResourceInUseException ex) {
            // table exsist, is noraml
        }

        return result;
    }
}
