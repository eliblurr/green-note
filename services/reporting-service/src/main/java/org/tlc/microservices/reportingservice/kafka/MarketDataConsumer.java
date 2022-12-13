package org.tlc.microservices.reportingservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.tlc.domain.base.kafka.KafkaConsumer;
import org.tlc.domain.base.marketData.ReportingServiceDto;
import org.tlc.domain.base.order.dto.CreateOrderDTO;
import org.tlc.microservices.reportingservice.services.OrderService;

public class MarketDataConsumer implements KafkaConsumer<ReportingServiceDto> {

    @Autowired
    private OrderService orderService;

    @Override
//    @Deprecated
    @KafkaListener(topics = "${spring.kafka.topic.reporting.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ReportingServiceDto obj) {
        System.out.println("\n\n"+obj.toString());
    }


    @KafkaListener(topics = "${spring.kafka.topic.reporting.order.create.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(CreateOrderDTO createOrderDTO) {
        System.out.println(createOrderDTO);
        orderService.create(createOrderDTO);
    }
}
