package com.ubs.producer.service;

import com.ubs.producer.dto.MessageQueue;

public interface AmqpService {
    void sendToConsumer(MessageQueue message);
}
