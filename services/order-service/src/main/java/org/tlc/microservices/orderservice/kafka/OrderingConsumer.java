package org.tlc.microservices.orderservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.kafka.KafkaConsumer;
import org.tlc.domain.base.marketData.OrderingServiceDto;

@Service
public class OrderingConsumer implements KafkaConsumer<OrderingServiceDto> {
    @Override
    @KafkaListener(topics = "${spring.kafka.topic.reporting.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderingServiceDto obj) {
        System.out.println("\n\n"+obj.toString());
    }
}
