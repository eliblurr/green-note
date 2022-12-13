package org.tlc.microservices.reportingservice.kafka;

import jdk.jshell.Snippet;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.kafka.KafkaConsumer;
import org.tlc.domain.base.marketData.ReportingServiceDto;
import org.tlc.domain.base.order.dto.CreateLegDTO;
import org.tlc.domain.base.order.dto.UpdateLegDTO;
import org.tlc.domain.base.order.enums.LegStatus;
import org.tlc.microservices.reportingservice.services.LegService;

import java.util.UUID;

@Service
public class ReportingConsumer implements KafkaConsumer<ReportingServiceDto> {
    @Autowired LegService legService;
    @Autowired private KafkaPublish KafkaPublish;
    @Autowired @Qualifier("reportTopic") private NewTopic topic;
    @Override
    @KafkaListener(topics = "${spring.kafka.topic.reporting.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ReportingServiceDto obj) {
        UUID orderId = UUID.fromString(obj.getOrderID());
        if (legService.legIdExist(orderId)) {
            if (obj.getCumQty() == legService.readById(orderId).getQuantity()) {
                legService.updateStatus(orderId, LegStatus.CLOSED);
            }

//            //publish to front end
//            KafkaPublish.setTopic(topic);
//            System.out.println(topic.name());
//            KafkaPublish.sendMessage(newData);
//            System.out.println("\n\n" + obj.toString());
        }
    }
}
