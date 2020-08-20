package com.example.producer01.controller.v1.api;


import com.example.producer01.controller.v1.request.KafkaMsg;
import com.example.producer01.controller.v1.request.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Person> personKafkaTemplate;

    @PostMapping
    KafkaMsg sendMsg(@RequestBody KafkaMsg msg){
        System.out.println(msg.getTopic());
        System.out.println(msg.getContent());
        personKafkaTemplate.send(msg.getTopic(), new Person("vuong", 23));
        return msg;
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

    public void sendMessage(String topicName, String message) {

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }
}
