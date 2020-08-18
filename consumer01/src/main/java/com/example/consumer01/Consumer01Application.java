package com.example.consumer01;

import com.example.consumer01.models.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@SpringBootApplication
public class Consumer01Application {

    public static void main(String[] args) {
        SpringApplication.run(Consumer01Application.class, args);
    }

    @Bean
    public MessageListener messageListener(){
        return new MessageListener();
    }

    public static class MessageListener {

        @KafkaListener(topics = "logging", groupId = "g1", containerFactory = "fooKafkaListenerContainerFactory")
        public void listenGroup1(String msg){
            System.out.println("Message: " + msg);
        }

        @KafkaListener(topics = "${kafka.consumer.topic}", groupId = "${kafka.consumer.group_id}", containerFactory = "fooKafkaListenerContainerFactory")
        public void listenWithHeader(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition){
            System.out.println("Received Message: " + message + " from partition: " + partition);

        }

        @KafkaListener(topics = "${kafka.consumer.topic}", containerFactory = "filterKafkaListenerContainerFactory")
        public void listenWithFilter(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition){
            System.out.println("Filtered Message: " + message + " from partition: " + partition);
        }

        @KafkaListener(topics = "greeting", groupId = "greeting", containerFactory = "personKafkaListenerContainerFactory")
        public void greetingListener(@Payload Person person) {
            System.out.println("Received greeting message: " + person);
        }
    }
}
