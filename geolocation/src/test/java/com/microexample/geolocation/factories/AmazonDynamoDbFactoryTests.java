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

import org.junit.*;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.microexample.geolocation.TestUtilites;

public class AmazonDynamoDbFactoryTests {

    @Test(timeout = 1000 * 60)
    public void createTableTest() {
        AmazonDynamoDB _dbContext = null;

        try {
            /*
            * The ProfileCredentialsProvider will return your [default]
            * credential profile by reading from the credentials file located at
            * (~/.aws/credentials).
            */
            AWSCredentials awsCredentials = null;
            try {
                awsCredentials = new ProfileCredentialsProvider().getCredentials();
            } catch (Exception e) {
                throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
                        + "Please make sure that your credentials file is at the correct "
                        + "location (~/.aws/credentials), and is in valid format.", e);
            }

            AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
            _dbContext = new AmazonDynamoDbFactory(credentialsProvider).create();

            String tableName = "Tests";
            DeleteTableRequest deleteTableRequest = new DeleteTableRequest(tableName);
            boolean deleting = TableUtils.deleteTableIfExists(_dbContext, deleteTableRequest);

            // TODO rewrite test

            if(deleting) return;

            String hashKeyName = "testId";
            CreateTableResult result = TestUtilites.createTable(_dbContext, tableName, hashKeyName);

            TableDescription tableDesc = result.getTableDescription();
            assertEquals(tableName, tableDesc.getTableName());
            assertEquals("[{AttributeName: " + hashKeyName + ",KeyType: HASH}]", tableDesc.getKeySchema().toString());
            assertEquals("[{AttributeName: " + hashKeyName + ",AttributeType: S}]",
                    tableDesc.getAttributeDefinitions().toString());
            assertEquals(Long.valueOf(1L), tableDesc.getProvisionedThroughput().getReadCapacityUnits());
            assertEquals(Long.valueOf(1L), tableDesc.getProvisionedThroughput().getWriteCapacityUnits());
            try {
                TableUtils.waitUntilExists(_dbContext, tableName);
            } catch (Exception ex) {
                Assert.fail(ex.getMessage());
            }
        } finally {
            _dbContext.shutdown();
        }
    }
}
