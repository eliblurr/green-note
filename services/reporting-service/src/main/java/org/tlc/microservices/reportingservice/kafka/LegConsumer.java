package org.tlc.microservices.reportingservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.tlc.domain.base.marketData.ReportingServiceDto;
import org.tlc.domain.base.order.dto.CreateLegDTO;
import org.tlc.domain.base.order.dto.UpdateLegDTO;
import org.tlc.domain.base.order.dto.UpdateOrderDTO;
import org.tlc.domain.base.order.enums.LegStatus;

import org.tlc.microservices.reportingservice.services.LegService;

import java.util.UUID;

public class LegConsumer {

    @Autowired private LegService legService;

    @KafkaListener(topics = "${spring.kafka.topic.reporting.leg.create.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(CreateLegDTO createLegDTO) {
        legService.create(createLegDTO);
    }

    @KafkaListener(topics = "${spring.kafka.topic.reporting.leg.update.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ReportingServiceDto obj) {
        UUID orderId = UUID.fromString(obj.getOrderID());

        if (legService.legIdExist(orderId)) {
            if (obj.getCumQty() == legService.readById(orderId).getQuantity()) {
                legService.updateStatus(orderId, LegStatus.CLOSED);
            }
        }

    }
}
