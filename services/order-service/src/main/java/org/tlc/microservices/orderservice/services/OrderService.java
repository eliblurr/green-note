package org.tlc.microservices.orderservice.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
import org.tlc.microservices.orderservice.strategy.DefaultOrderProcessor;
import org.tlc.microservices.orderservice.strategy.OrderProcessor;

@Service
public class OrderService {

//    @Autowired
//    private WebClient webClient;

    private final WebClient webClient;
    @Autowired
    private DefaultOrderProcessor defaultOrderProcessor;

    public OrderService(WebClient.Builder  webClientBuilder){
        this.webClient = webClientBuilder
                .baseUrl("https://exchange2.matraining.com/d8e53ba5-0661-4013-bf98-b950ae1f9224/order")
                .build();
    }

    private static final ModelMapper modelMapper = new ModelMapper();


    public void saveOrder(OrderRequestDTO orderRequest) {
        //send to reporting service
        //this webclient will be replaced with a redis publisher
    }


}
