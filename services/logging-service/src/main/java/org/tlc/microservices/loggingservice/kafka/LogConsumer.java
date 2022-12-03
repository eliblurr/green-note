package org.tlc.microservices.loggingservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.kafka.KafkaConsumer;
import org.tlc.domain.base.logging.dto.LogEventDTO;

@Service
public class LogConsumer implements KafkaConsumer<LogEventDTO> {

    @Override
    @KafkaListener(topics = "${spring.kafka.topic.logging.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(LogEventDTO obj) {

        System.out.println("\n\n"+obj.toString());

//        save to database here

    }
}
