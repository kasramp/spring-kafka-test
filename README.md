# Spring Kafka test example

Spring Kafka example with JUnit 5 using EmbeddedKafka/`spring-kafka-test` and also using [Testcontainers](https://www.testcontainers.org/).

For the tutorials check the links below,

- [Test Spring Kafka consumer and producer with EmbeddedKafka](https://www.geekyhacker.com/test-spring-kafka-consumer-and-producer-with-embeddedkafka/)
- [Write Kafka integration test with Testcontainers](https://www.geekyhacker.com/write-kafka-integration-test-with-testcontainers/)
- [Database migration with Spring Boot and Flyway](https://www.geekyhacker.com/database-migration-with-spring-boot-and-flyway/)
- [Spring Boot MySQL integration tests with Testcontainers](https://www.geekyhacker.com/spring-boot-mysql-integration-tests-with-testcontainers/)
- [Spring Boot Swagger documentation using OpenAPI](https://www.geekyhacker.com/spring-boot-swagger-documentation-using-openapi/)
- [Disable Springdoc OpenAPI Swagger in production](https://www.geekyhacker.com/disable-springdoc-openapi-swagger-in-production/)
- [How to hide an endpoint in Springdoc OpenAPI](https://www.geekyhacker.com/how-to-hide-an-endpoint-in-springdoc-openapi/)

## How to run

First start the docker-compose which contains ZooKeeper, Kafka, Kafdrop, and MySQL.

```bash
$ docker-compose -f docker-compose.yml up -d
```

Once all the containers are healthy start the application,

```bash
$ ./mvnw spring-boot:run
```

Open the browser `localhost:8080/apidocs`. 

You can interact with the `random` API to create a random user which then will be sent to Kafka and consumed by the consumer (see [`consumer/UserKafkaListener.java`](https://github.com/kasramp/spring-kafka-test/blob/master/src/main/java/com/madadipouya/springkafkatest/consumer/UserKafkaListener.java) file) and finally saves into the database.

To see whether the message has been sent to Kafka, open your browser `http://localhost:8085/topic/com.madadipouya.kafka.use` (Kafdrop environment), 
you should be able to see all messages that sent to `kafka.user` topic.

To run Flyway migration scripts only run,

```bash
$ ./mvnw flyway:migrate
```
