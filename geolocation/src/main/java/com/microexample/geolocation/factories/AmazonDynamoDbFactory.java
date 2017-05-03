package com.microexample.geolocation.factories;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.*;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.*;
import com.microexample.geolocation.contracts.IFactory;

// https://aws.amazon.com/blogs/developer/fluent-client-builders/

public class AmazonDynamoDbFactory implements IFactory<AmazonDynamoDB> {
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
    @Override
    public AmazonDynamoDB create() {
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

        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                // TODO add regional destribution logic
                .withRegion(Regions.US_WEST_2).withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

        return amazonDynamoDB;
    }
}
