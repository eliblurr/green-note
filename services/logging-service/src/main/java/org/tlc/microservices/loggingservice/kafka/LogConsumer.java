package org.tlc.microservices.loggingservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.kafka.KafkaConsumer;
import org.tlc.domain.base.logging.dto.LogEventDTO;
import org.tlc.microservices.loggingservice.service.LogService;

@Service
public class LogConsumer implements KafkaConsumer<LogEventDTO> {

    @Autowired private LogService logService;

    @Override
    @KafkaListener(topics = "${spring.kafka.topic.logging.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(LogEventDTO obj) {

        // save to database here
        logService.create(obj.getLogDTO().getMessage(), obj.getLogDTO().getUser());

    }
}
