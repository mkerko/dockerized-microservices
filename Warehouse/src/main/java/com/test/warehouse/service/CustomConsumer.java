package com.test.warehouse.service;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class CustomConsumer extends DefaultConsumer {
    public CustomConsumer(Channel channel) {
        super(channel);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
            throws IOException {
        String message = new String(body, "UTF-8");
        log.info(" [x] Received '" + message + "'");
    }
}
