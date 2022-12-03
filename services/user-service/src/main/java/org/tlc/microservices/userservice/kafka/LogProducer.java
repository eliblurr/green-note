package org.tlc.microservices.userservice.kafka;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.kafka.KafkaProducer;
import org.tlc.domain.base.logging.dto.LogDTO;
import org.tlc.domain.base.logging.dto.LogEventDTO;

@Service
@AllArgsConstructor
public class LogProducer extends KafkaProducer<LogDTO, LogEventDTO> {
    @Autowired @Qualifier("logTopic") private NewTopic topic;
}
