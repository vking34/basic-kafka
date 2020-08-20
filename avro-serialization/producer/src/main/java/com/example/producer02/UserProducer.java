package com.example.producer02;

import com.example.kafka.User;
//import io.confluent.developer.User;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@CommonsLog(topic = "Producer Logger")
public class UserProducer {

    @Value("${topic.name}")
    private String TOPIC;

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

//    public UserProducer(KafkaTemplate<String, User> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }

    public void sendMessage(User user) {
        this.kafkaTemplate.send(this.TOPIC, user.getName(), user);
        log.info(String.format("Produced user -> %s", user));
    }

}
