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
1. Run bash script from project root folder

```bash
sh scripts/setup-permissions.sh
```

## Launch

```bash
sh scripts/dev/run.sh
```

Or in `vs code` press `F1` -> print `tasks` -> press `Enter` -> print `run` -> press `Enter`


## Links

### [Amazon](https://aws.amazon.com)

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
