package org.tlc.microservices.reportingservice.kafka;

import org.bouncycastle.math.raw.Mod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.kafka.KafkaConsumer;
import org.tlc.domain.base.order.dto.CreateOrderDTO;
import org.tlc.domain.base.order.dto.SaveOrderDTO;
import org.tlc.domain.base.order.dto.UpdateOrderDTO;
import org.tlc.microservices.reportingservice.services.OrderService;

@Service
public class OrderConsumer implements KafkaConsumer<SaveOrderDTO> {
    @Autowired
    private OrderService orderService;

    ModelMapper modelMapper = new ModelMapper();

    @KafkaListener(topics = "${spring.kafka.topic.reporting.order.create.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(SaveOrderDTO saveOrderDTO) {
        System.out.println("Received at reporting service: " + saveOrderDTO);
        orderService.create(new CreateOrderDTO(saveOrderDTO));
    }

    @KafkaListener(topics = "${spring.kafka.topic.reporting.order.update.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(UpdateOrderDTO updateOrderDTO) {
        orderService.updateStatus(updateOrderDTO.getOrderId(), updateOrderDTO.getStatus());
    }

//    topic
//    check if order belongs to us
//    puish to notification
//    do partial update

}