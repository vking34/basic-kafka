package com.example.producer02;


import com.example.kafka.User;
//import io.confluent.developer.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaController {
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    @Autowired
//    private KafkaTemplate<String, Person> personKafkaTemplate;

    @Autowired
    private UserProducer userProducer;

//    @PostMapping
//    KafkaMsg sendMsg(@RequestBody KafkaMsg msg){
//        System.out.println(msg.getTopic());
//
//        Person person = msg.getPerson();
//        System.out.println(person.getName());
//        personKafkaTemplate.send(msg.getTopic(), person);
//        return msg;
//    }

    @GetMapping("/publish")
    public void sendMessageToKafkaTopic(@RequestParam("name") String name, @RequestParam("age") Integer age){
        userProducer.sendMessage(new User(name, age));
    }


//    public void sendPersonMessage(String topicName, Person person) {
//
//        ListenableFuture<SendResult<String, Person>> future = personKafkaTemplate.send(topicName, person);
//
//        future.addCallback(new ListenableFutureCallback<SendResult<String, Person>>() {
//
//            @Override
//            public void onSuccess(SendResult<String, String> result) {
//                System.out.println("Sent message=[" + person.getName() +
//                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
//            }
//            @Override
//            public void onFailure(Throwable ex) {
//                System.out.println("Unable to send message=["
//                        + person.getName() + "] due to : " + ex.getMessage());
//            }
//        });
//    }

//    public void sendMessage(String topicName, String message) {
//
//        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
//
//        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
//
//            @Override
//            public void onSuccess(SendResult<String, String> result) {
//                System.out.println("Sent message=[" + message +
//                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
//            }
//            @Override
//            public void onFailure(Throwable ex) {
//                System.out.println("Unable to send message=["
//                        + message + "] due to : " + ex.getMessage());
//            }
//        });
//    }
}
