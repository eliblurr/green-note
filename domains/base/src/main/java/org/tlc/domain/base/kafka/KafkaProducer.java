package org.tlc.domain.base.kafka;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;

@Setter
@AllArgsConstructor
public abstract class KafkaProducer <E> {

    protected NewTopic topic;
    protected KafkaTemplate kafkaTemplate;

    public void sendMessage(E event){
        kafkaTemplate.send( MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC, topic.name()).build());
    }

//    public KafkaProducer(NewTopic topic, KafkaTemplate kafkaTemplate){
//        this.topic = topic;
//        this.kafkaTemplate = kafkaTemplate;
//    }

}
