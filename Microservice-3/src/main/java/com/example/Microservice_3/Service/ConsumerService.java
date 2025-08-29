package com.example.Microservice_3.Service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {


    @KafkaListener(topics = "demo_topic",groupId = "demo-group-1")
    public void consumeKafkaMessage(String message){
        System.out.println("Message Received from topic : " + message);

    }

}
