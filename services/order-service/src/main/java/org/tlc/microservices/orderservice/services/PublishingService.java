package org.tlc.microservices.orderservice.services;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.order.dto.SaveOrderDTO;
import org.tlc.domain.base.order.dto.SaveLegDTO;
import org.tlc.microservices.orderservice.services.producers.LegPublisher;
import org.tlc.microservices.orderservice.services.producers.OrderPublisher;

import java.util.ArrayList;
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

    public void saveTrades(List<SaveLegDTO> legs){
        legPublisher.setTopic(legTopic);
        System.out.println(legTopic.name());
        System.out.println("********************SAVING LEGS TO DB************");
        legPublisher.sendMessage(legs);
    }

    public void saveTrades(SaveLegDTO leg){
        legPublisher.setTopic(legTopic);
        System.out.println(legTopic.name());
        System.out.println("********************SAVING LEGS TO DB************");
        List<SaveLegDTO> legs = new ArrayList<SaveLegDTO>();
        legs.add(leg);
        legPublisher.sendMessage(legs);
    }
}
