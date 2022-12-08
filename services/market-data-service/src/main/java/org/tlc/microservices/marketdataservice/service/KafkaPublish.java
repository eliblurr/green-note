package org.tlc.microservices.marketdataservice.service;

import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.kafka.KafkaProducer;
import org.tlc.domain.base.marketData.ReportingServiceDto;


@Service
@Getter
@Setter
public class KafkaPublish extends KafkaProducer<ReportingServiceDto> {
        public KafkaPublish(@Autowired @Qualifier("reportTopic") NewTopic topic, KafkaTemplate<String, ReportingServiceDto> kafkaTemplate) {
            super(topic, kafkaTemplate);
        }
    }

