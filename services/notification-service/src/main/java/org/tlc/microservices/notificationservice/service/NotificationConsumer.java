package org.tlc.microservices.notificationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.kafka.KafkaConsumer;
import org.tlc.domain.base.marketData.ReportingServiceDto;


@Service
public class NotificationConsumer implements KafkaConsumer<ReportingServiceDto> {

    @Autowired SimpMessagingTemplate simpMessagingTemplate;

    @Override
    @KafkaListener(topics = "${spring.kafka.topic.reporting.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ReportingServiceDto obj) {

        System.out.println("\n\n"+obj.toString());
        simpMessagingTemplate.convertAndSend("/topic/tickers", obj.toString());
    }
}
