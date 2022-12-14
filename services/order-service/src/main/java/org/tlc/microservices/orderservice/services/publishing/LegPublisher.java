package org.tlc.microservices.orderservice.services.publishing;

import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.kafka.KafkaProducer;
import org.tlc.domain.base.order.dto.SaveLegDTO;

import java.util.List;


@Service
@Getter
@Setter
public class LegPublisher extends KafkaProducer<List<SaveLegDTO>> {

    public LegPublisher(@Autowired @Qualifier("createLegTopic") NewTopic topic, KafkaTemplate<String, List<SaveLegDTO>> kafkaTemplate) {
        super(topic, kafkaTemplate);
    }
}
