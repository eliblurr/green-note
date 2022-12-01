package org.tlc.microservices.orderservice.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class OrderService {

    @Autowired
    private WebClient webClient;

    private static final ModelMapper modelMapper = new ModelMapper();

    public void placeOrder(OrderRequestDTO orderRequestDTO){
    //send to exchange

    }
    public void saveOrder(OrderRequestDTO orderRequest){
        //send to reporting service
        //this webclient will be replaced with a redis publisher
        webClient.post().uri("/orders")
                .body(Mono.just(orderRequest),OrderRequestDTO.class)
                .retrieve().bodyToMono(OrderRequestDTO.class)
                .timeout(Duration.ofMillis(10_000));
    }


}
