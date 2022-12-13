package org.tlc.microservices.reportingservice.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.kafka.KafkaConsumer;
import org.tlc.domain.base.marketData.ReportingServiceDto;

@Service
public class ReportingConsumer implements KafkaConsumer<ReportingServiceDto> {
    @Override
    @KafkaListener(topics = "${spring.kafka.topic.reporting.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ReportingServiceDto obj) {
//        if (obj.getOrderID().equals()){
//
//        }
        System.out.println("\n\n"+obj.toString());
    }
}
