package com.example.producer01.controller.v1.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class KafkaMsg {
    private String topic;
    private String content;
}
