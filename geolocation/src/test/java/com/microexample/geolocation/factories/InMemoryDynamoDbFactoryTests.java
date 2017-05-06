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

import org.junit.Test;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import com.microexample.geolocation.TestUtilites;

public class InMemoryDynamoDbFactoryTests {

    @Test
    public void createTableTest() {
        AmazonDynamoDB _dbContext = null;

        try {
            _dbContext = new InMemoryDynamoDbFactory().create();

            String tableName = "Tests";
            String hashKeyName = "testId";
            CreateTableResult res = TestUtilites.createTable(_dbContext, tableName, hashKeyName);

            TableDescription tableDesc = res.getTableDescription();
            assertEquals(tableName, tableDesc.getTableName());
            assertEquals("[{AttributeName: " + hashKeyName + ",KeyType: HASH}]", tableDesc.getKeySchema().toString());
            assertEquals("[{AttributeName: " + hashKeyName + ",AttributeType: S}]",
                    tableDesc.getAttributeDefinitions().toString());
            assertEquals(Long.valueOf(1L), tableDesc.getProvisionedThroughput().getReadCapacityUnits());
            assertEquals(Long.valueOf(1L), tableDesc.getProvisionedThroughput().getWriteCapacityUnits());
            assertEquals("arn:aws:dynamodb:ddblocal:000000000000:table/Tests", tableDesc.getTableArn());

            ListTablesResult tables = _dbContext.listTables();
            assertEquals(1, tables.getTableNames().size());
        } finally {
            _dbContext.shutdown();
        }
    }
}
