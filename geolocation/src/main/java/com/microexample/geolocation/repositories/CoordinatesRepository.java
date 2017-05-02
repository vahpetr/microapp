package com.microexample.geolocation.repositories;

import org.springframework.stereotype.Repository;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.amazonaws.services.dynamodbv2.util.TableUtils.TableNeverTransitionedToStateException;
import com.microexample.geolocation.contracts.ICoordinatesRepository;
import com.microexample.geolocation.models.*;

@Repository
public class CoordinatesRepository implements ICoordinatesRepository {

    private final AmazonDynamoDB _amazonDynamoDB;

    /**
     * The only information needed to create a client are security credentials
     * consisting of the AWS Access Key ID and Secret Access Key. All other
     * configuration, such as the service endpoints, are performed
     * automatically. Client parameters, such as proxies, can be specified in an
     * optional ClientConfiguration object when constructing a client.
     *
     * @see com.amazonaws.auth.BasicAWSCredentials
     * @see com.amazonaws.auth.ProfilesConfigFile
     * @see com.amazonaws.ClientConfiguration
     */
    public CoordinatesRepository() {
        // https://aws.amazon.com/blogs/developer/fluent-client-builders/

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

        _amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                // TODO add regional destribution logic
                .withRegion(Regions.US_WEST_2).withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

    public void save(Coordinate coordinate) {

    }

    private void createTableIfNotExists() throws InterruptedException, TableNeverTransitionedToStateException {
        String tableName = "coordinates";

        CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName);

        // TODO add tabla shema

        TableUtils.createTableIfNotExists(_amazonDynamoDB, createTableRequest);
        TableUtils.waitUntilActive(_amazonDynamoDB, tableName);
    }
}
