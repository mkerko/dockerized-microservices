package com.test.warehouse.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class RabbitService {
    private final static String QUEUE_NAME = "hello";

    public void receive() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("rabbit");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        log.info(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new CustomConsumer(channel);
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
