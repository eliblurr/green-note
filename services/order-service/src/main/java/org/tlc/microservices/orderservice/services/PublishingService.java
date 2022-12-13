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
    private OrderPublisher orderPublisher;
    @Autowired
    private LegPublisher legPublisher;

    @Autowired @Qualifier("createOrderTopic") private NewTopic topic;

    @Autowired @Qualifier("createLegTopic") private NewTopic legTopic;



    public void saveOrder(SaveOrderDTO order) {
        orderPublisher.setTopic(topic);
        System.out.println(topic.name());
        orderPublisher.sendMessage(order);

        System.out.println("********************SAVING ORDER TO DB************");

    }

    public void saveTrades(List<SaveTradeDTO> trades){
        legPublisher.setTopic(legTopic);

        System.out.println("********************SAVING TRADES TO DB************");
        for(SaveTradeDTO trade : trades){
            legPublisher.sendMessage(trade);
        }


    }
}
