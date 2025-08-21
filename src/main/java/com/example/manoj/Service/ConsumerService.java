package com.example.manoj.Service;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {


    @KafkaListener(topics = "demo_topic",groupId = "demo_group")
    public void consumeKafkaMessage(String message){
         System.out.println("Message Received from topic : " + message);

    }

}
