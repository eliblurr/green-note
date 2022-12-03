package org.tlc.domain.base.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;

public abstract class KafkaProducer <T, E> {

    private NewTopic topic;
    private KafkaTemplate<String, T> kafkaTemplate;

    public void sendMessage(E event){
        kafkaTemplate.send( MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC, topic.name()).build());
    }

}
