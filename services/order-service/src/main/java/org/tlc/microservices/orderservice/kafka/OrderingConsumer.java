package org.tlc.microservices.orderservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.kafka.KafkaConsumer;
import org.tlc.domain.base.marketData.OrderingServiceDto;
import org.tlc.microservices.orderservice.msdata.BuySideData;
import org.tlc.microservices.orderservice.msdata.MarketServiceData;
import org.tlc.microservices.orderservice.msdata.SellSideData;
import org.tlc.microservices.orderservice.services.OrderValidator;

@Service
public class OrderingConsumer implements KafkaConsumer<OrderingServiceDto> {
    @Autowired
    MarketServiceData marketServiceData;
    @Autowired
    OrderValidator orderValidator;
    @Override
    @KafkaListener(topics = "${spring.kafka.topic.reporting.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderingServiceDto obj) {
        orderValidator.setMarketData(obj);

        System.out.println("\n\n"+obj.toString());
    }
}
