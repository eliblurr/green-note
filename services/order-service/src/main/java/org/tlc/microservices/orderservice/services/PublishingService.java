package org.tlc.microservices.orderservice.services;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.order.dto.SaveOrderDTO;
import org.tlc.microservices.orderservice.dto.SaveTradeDTO;

import java.util.List;

@Service
public class PublishingService {

    @Autowired
    private KafkaPublisher kafkaPublisher;

    @Autowired @Qualifier("createOrderTopic") private NewTopic topic;



    public void saveOrder(SaveOrderDTO order) {
        kafkaPublisher.setTopic(topic);
        System.out.println(topic.name());
        kafkaPublisher.sendMessage(order);

        System.out.println("SAVING TO DB*/**/*/*");


    }

    public void saveTrades(SaveTradeDTO tradeDTO){

    }

    public void saveTrades(List<SaveTradeDTO> trades){

    }
}
