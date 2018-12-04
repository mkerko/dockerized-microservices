package com.test.store.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Log4j2
@Component
public class RabbitService {

    private static final String QUEUE_NAME ="hello";
    private static final String RABBIT_HOST = "rabbit";
    private static final String MESSAGE = "rabbit";

    public boolean send() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBIT_HOST);
        @Cleanup() Connection connection = factory.newConnection();
        @Cleanup() Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.basicPublish("", QUEUE_NAME, null, MESSAGE.getBytes());
        log.info(" [x] Sent '" + MESSAGE + "'");
        return true;
    }
}
