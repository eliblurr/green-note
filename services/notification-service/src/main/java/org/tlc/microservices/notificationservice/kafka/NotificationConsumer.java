package org.tlc.microservices.notificationservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.kafka.KafkaConsumer;
import org.tlc.domain.base.notification.dto.SocketPubSubDTO;
import org.tlc.microservices.notificationservice.service.NotificationService;

@Service
public class NotificationConsumer implements KafkaConsumer<SocketPubSubDTO> {

    @Autowired private NotificationService notificationService;

    @Override
    @KafkaListener(topics = "spring.kafka.topic.notification.transactions.name", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(SocketPubSubDTO obj) { notificationService.sendMessage(obj.getContent());}

}
