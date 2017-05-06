package com.microexample.geolocation.factories;

import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.*;
import com.microexample.geolocation.contracts.IFactory;

// https://aws.amazon.com/blogs/developer/fluent-client-builders/
// http://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html
// http://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-credentials.html

public class AmazonDynamoDbFactory implements IFactory<AmazonDynamoDB> {

    private final AWSCredentialsProvider _awsCredentialsProvider;

    @Autowired
    public AmazonDynamoDbFactory(AWSCredentialsProvider awsCredentialsProvider) {
        _awsCredentialsProvider = awsCredentialsProvider;
    }

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
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                // TODO add regional destribution logic
                .withRegion(Regions.US_WEST_2).withCredentials(_awsCredentialsProvider)
                .build();

        return amazonDynamoDB;
    }
}
