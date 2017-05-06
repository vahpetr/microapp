# microapp

This example project - cloud-based microservices scalable app.

Configured development environment only for osx.

Compatibility with [Visual Studio Code](https://code.visualstudio.com/).

Used technologies:

* Cloud - [Amazon EC2](https://aws.amazon.com/ec2/)
* Container - [Docker](https://www.docker.com/)
* Load Balancers:

  * Internal - [Docker Swarm](https://docs.docker.com/engine/swarm)

* Backend - [Java Spring](https://spring.io/)
* Database - [Amazon DynamoDB](https://aws.amazon.com/dynamodb/)

## Configuration

1. Add `regisry.local:5000` in insecure registry on docker config
1. Setup permissions `sh scripts/setup-permissions.sh`
1. Register for the [Amazon](https://console.aws.amazon.com/ec2/v2/home)
1. Create individual [IAM users](https://console.aws.amazon.com/iam/)
1. Set local [credentials](http://docs.aws.amazon.com/cli/latest/userguide/cli-chap-getting-started.html)  profile `aws configure`
1. Setup amazon env `sh scripts/setup-amazon-env.sh`

## Launch

### In vscode

`F1` -> print `tasks` -> `Enter` -> print `run` -> `Enter`

or

```bash
sh scripts/dev/run.sh
```

## Scale

You can scale the stack [dynamically](https://docs.docker.com/engine/reference/commandline/service_scale/#scale-a-single-service)

```bash
docker service scale microapp_geolocation=6
```

## Develpment

### How it use in vscode

* Build project - `cmd+shift+b`
* Test project - `cmd+shoft+t`
* Task list - `F1` -> print `tasks` -> `Enter`
* Open terminal - `ctrl+~`
* Run geolocation service without docker - `F1` -> print `tasks` -> `Enter` -> print `run-geolocation` -> `Enter`

I did not find a simple way to run one test, but you can run custom test manualy. Example:

```bash
mvn -Dtest=com.microexample.geolocation.GeolocationApplicationTests#contextLoads test
```

## Links

### [Amazon](https://aws.amazon.com)

* [EC2](https://aws.amazon.com/documentation/ec2/)
* [EC2 setup](http://docs.aws.amazon.com/AWSEC2/latest/UserGuide/get-set-up-for-amazon-ec2.html)
* [Java sdk](https://aws.amazon.com/sdk-for-java)
* [Java sdk sources](https://github.com/aws/aws-sdk-java)
* [Java samples](https://github.com/aws/aws-sdk-java/tree/master/src/samples)
* [Java DynamoDB sample](https://github.com/aws/aws-sdk-java/tree/master/src/samples/AmazonDynamoDB)

### [Docker](https://www.docker.com/)

* [Docs](https://docs.docker.com)
* [Swarm](https://docs.docker.com/engine/swarm)
* [Configuration external load balancer](https://docs.docker.com/engine/swarm/ingress/#configure-an-external-load-balancer)


### [Spring](https://spring.io/)

* [Spring simple guide](https://ru.wikibooks.org/wiki/Spring_Framework_Guide)
* [Spring guid](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/index.html)
* [Spring guid mvc](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html)
