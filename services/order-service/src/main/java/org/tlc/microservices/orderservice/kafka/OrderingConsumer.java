package org.tlc.microservices.orderservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.kafka.KafkaConsumer;
import org.tlc.domain.base.marketData.OrderingServiceDto;
import org.tlc.microservices.orderservice.data.MarketData;
import org.tlc.microservices.orderservice.services.OrderProcessorStrategyPicker;

@Service
public class OrderingConsumer implements KafkaConsumer<OrderingServiceDto> {
    @Autowired
    MarketData marketData;

    @Autowired
    OrderProcessorStrategyPicker orderProcessorStrategyPicker;
    @Override
    @KafkaListener(topics = "${spring.kafka.topic.reporting.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderingServiceDto obj) {
        marketData.setMarketData(obj);
    }
}
