package org.tlc.microservices.reportingservice.kafka;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.tlc.domain.base.marketData.ReportingServiceDto;
import org.tlc.domain.base.order.dto.*;
import org.tlc.domain.base.order.enums.LegStatus;

import org.tlc.microservices.reportingservice.services.LegService;

import java.util.List;
import java.util.UUID;

public class LegConsumer {

    @Autowired
    private LegService legService;
    @Autowired
    ModelMapper modelMapper;

    @KafkaListener(topics = "${spring.kafka.topic.reporting.leg.create.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(List<SaveLegDTO> legs) {
        System.out.println("Received Leg at reporting service: " + legs);
        for (SaveLegDTO leg : legs) {
            legService.create(modelMapper.map(leg, CreateLegDTO.class));
        }
    }

    @KafkaListener(topics = "${spring.kafka.topic.reporting.leg.update.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ReportingServiceDto obj) {

        System.out.println(obj.toString());

        UUID orderId = UUID.fromString(obj.getOrderID());

        if (legService.legIdExist(orderId)) {
            if (obj.getCumQty() == legService.readById(orderId).getQuantity()) {
                legService.updateStatus(orderId, LegStatus.CLOSED);
            }
        }

    }
}
