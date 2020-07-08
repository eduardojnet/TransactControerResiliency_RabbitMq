package com.ubs.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@Embeddable
public class MessageQueue {
    private String text;

    public MessageQueue() {}

}