package com.example.consumer03;

//import io.confluent.developer.User;
import com.example.kafka.User;
import lombok.extern.apachecommons.CommonsLog;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@CommonsLog(topic = "Consumer Logger")
public class Consumer {

  @Value("${topic.name}")
  private String topicName;


  @KafkaListener(topics = "users", groupId = "group_id")
  public void consume(@Payload(required = false) User user) {
    log.info(String.format("Consumed message -> %s", user.getName()));
    System.out.println(user.getAge());
//    System.out.println(record.value().getAge());
//    try {
//      User user = objectMapper.readValue(record.value().toString(), User.class);
//      System.out.println(user.getAge());
//    }
//    catch (IOException e){
//      e.printStackTrace();
//    }
  }

//  @KafkaListener(topics = "users", groupId = "group_id")
//  public void consume(User record) {
//    log.info(String.format("Consumed message -> %s", record);
//  }
}