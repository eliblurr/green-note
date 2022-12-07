package org.tlc.microservices.userservice.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.kafka.KafkaProducer;
import org.tlc.domain.base.logging.dto.LogDTO;
import org.tlc.domain.base.logging.dto.LogEventDTO;

@Service
@Getter
@Setter
public class LogProducer extends KafkaProducer<LogEventDTO> {
    public LogProducer(@Autowired @Qualifier("logTopic") NewTopic topic, KafkaTemplate<String, LogEventDTO> kafkaTemplate) {
        super(topic, kafkaTemplate);
    }
}
