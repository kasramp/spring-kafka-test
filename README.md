# Spring Kafka test example

Spring Kafka example with junit test using EmbeddedKafka.

## How to run

First start the docker-compose which contains ZooKeeper, Kafka, and Kafdrop.

```bash
$ docker-compose -f docker-compose.yml up -d
```

Once all the containers are healthy start the application,

```bash
$ ./mvnw spring-boot:run
```

Open the browser `localhost:8080/apidocs`. 

You can interact with the `random` api to create a random user which then will be sent to Kafka and consumed by the consumer (see [`consumer/UserKafkaListener.java`](https://github.com/kasramp/spring-kafka-test/blob/master/src/main/java/com/madadipouya/springkafkatest/consumer/UserKafkaListener.java) file).

To see whether the message has been sent to Kafka, open your browser `http://localhost:8085/topic/com.madadipouya.kafka.use` (Kafdrop environment), 
you should be able to see all messages that sent to `kafka.user` topic.  
