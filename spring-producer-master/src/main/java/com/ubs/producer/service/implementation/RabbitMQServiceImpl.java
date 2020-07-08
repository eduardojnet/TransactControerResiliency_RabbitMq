package com.ubs.producer.service.implementation;

import com.ubs.producer.amqp.AmqpProducer;
import com.ubs.producer.dto.MessageQueue;
import com.ubs.producer.service.AmqpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQServiceImpl implements AmqpService {

    @Autowired
    private AmqpProducer<MessageQueue> amqp;

    @Override
    public void sendToConsumer(MessageQueue message) {
        amqp.producer(message);
    }
}
