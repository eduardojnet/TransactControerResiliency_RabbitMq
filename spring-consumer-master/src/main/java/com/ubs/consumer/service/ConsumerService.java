package com.ubs.consumer.service;

import com.ubs.consumer.dto.MessageQueue;

public interface ConsumerService {

    void action(MessageQueue message);

}
