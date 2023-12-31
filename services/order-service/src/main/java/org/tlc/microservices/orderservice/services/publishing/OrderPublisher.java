package org.tlc.microservices.orderservice.services.publishing;

import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.kafka.KafkaProducer;
import org.tlc.domain.base.order.dto.SaveOrderDTO;


@Service
@Getter
@Setter
public class OrderPublisher extends KafkaProducer<SaveOrderDTO> {

    public OrderPublisher(@Autowired @Qualifier("createOrderTopic") NewTopic topic, KafkaTemplate<String, SaveOrderDTO> kafkaTemplate) {
        super(topic, kafkaTemplate);
    }
}
