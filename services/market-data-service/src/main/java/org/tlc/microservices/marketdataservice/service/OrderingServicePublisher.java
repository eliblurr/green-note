package org.tlc.microservices.marketdataservice.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.kafka.KafkaProducer;
import org.tlc.domain.base.marketData.OrderingServiceDto;

@Service
public class OrderingServicePublisher extends KafkaProducer<OrderingServiceDto> {
public OrderingServicePublisher(@Autowired @Qualifier("orderTopic") NewTopic topic, KafkaTemplate<String, OrderingServiceDto> kafkaTemplate) {
        super(topic, kafkaTemplate);
        }
}
