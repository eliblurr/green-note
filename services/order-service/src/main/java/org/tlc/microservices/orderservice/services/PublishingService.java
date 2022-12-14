package org.tlc.microservices.orderservice.services;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.order.dto.SaveOrderDTO;
import org.tlc.domain.base.order.dto.SaveLegDTO;
import org.tlc.microservices.orderservice.services.publishing.LegPublisher;
import org.tlc.microservices.orderservice.services.publishing.OrderPublisher;

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

    public void saveLegs(List<SaveLegDTO> legs){
        legPublisher.setTopic(legTopic);
        System.out.println(legTopic.name());
        legPublisher.sendMessage(legs);
        System.out.println("********************SAVING LEGS TO DB************");
    }

    public void saveLegs(SaveLegDTO leg){
        legPublisher.setTopic(legTopic);
        System.out.println(legTopic.name());
        List<SaveLegDTO> legs = new ArrayList<SaveLegDTO>();
        legs.add(leg);
        legPublisher.sendMessage(legs);
        System.out.println(leg.toString());
        System.out.println("********************SAVING LEGS TO DB************");
    }
}
