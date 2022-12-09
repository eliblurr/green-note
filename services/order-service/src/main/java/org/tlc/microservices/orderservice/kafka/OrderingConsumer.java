package org.tlc.microservices.orderservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.kafka.KafkaConsumer;
import org.tlc.domain.base.marketData.ReportingServiceDto;

@Service
public class OrderingConsumer implements KafkaConsumer<ReportingServiceDto> {
    @Override
    @KafkaListener(topics = "${spring.kafka.topic.reporting.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ReportingServiceDto obj) {
        System.out.println("\n\n"+obj.toString());
    }
}
