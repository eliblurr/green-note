/*
package org.tlc.microservices.orderservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.kafka.KafkaConsumer;
import org.tlc.domain.base.marketData.OrderingServiceDto;
import org.tlc.domain.base.order.dto.SaveOrderDTO;

@Service
public class LocalOrderConsumer  implements KafkaConsumer<SaveOrderDTO> {

    @Override
    @KafkaListener(topics = "${spring.kafka.topic.order.local.create}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(SaveOrderDTO obj) {
        // publish order to webclient here
    }

}
*/
