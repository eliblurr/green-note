package org.tlc.microservices.orderservice.services;

import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.kafka.KafkaProducer;
import org.tlc.microservices.orderservice.dto.SaveLegDTO;


@Service
@Getter
@Setter
public class LegPublisher extends KafkaProducer<SaveLegDTO> {

    public LegPublisher(@Autowired @Qualifier("createLegTopic") NewTopic topic, KafkaTemplate<String, SaveLegDTO> kafkaTemplate) {
        super(topic, kafkaTemplate);
    }
}
