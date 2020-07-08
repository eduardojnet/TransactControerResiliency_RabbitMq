package com.ubs.producer.amqp;

public interface AmqpProducer<T> {
    void producer(T t);
}
