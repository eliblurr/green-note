package org.tlc.microservices.reportingservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.tlc.domain.base.order.dto.CreateLegDTO;
import org.tlc.domain.base.order.dto.UpdateLegDTO;
import org.tlc.microservices.reportingservice.services.LegService;

public class LegConsumer {

    @Autowired private LegService legService;

    @KafkaListener(topics = "${spring.kafka.topic.reporting.leg.create.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(CreateLegDTO createLegDTO) {
        legService.create(createLegDTO);
    }

    @KafkaListener(topics = "${spring.kafka.topic.reporting.leg.update.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(UpdateLegDTO updateLegDTO) {
        legService.updateStatus(updateLegDTO.getTradeId(), updateLegDTO.getStatus());
    }
}
