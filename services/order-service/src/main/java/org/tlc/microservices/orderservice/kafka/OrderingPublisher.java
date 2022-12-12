package org.tlc.microservices.orderservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.tlc.domain.base.kafka.KafkaProducer;
import org.tlc.domain.base.order.dto.SaveOrderDTO;

public class OrderingPublisher extends KafkaProducer<SaveOrderDTO> {
    public OrderingPublisher(@Autowired @Qualifier("saveOrderTopic") NewTopic topic, KafkaTemplate<String, SaveOrderDTO> kafkaTemplate) {
        super(topic, kafkaTemplate);
    }
}
