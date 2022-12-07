package org.tlc.microservices.orderservice.services.processingstrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.microservices.orderservice.configuration.WebClientConfiguration;
import org.tlc.microservices.orderservice.dto.CreateOrderOnExchangeDTO;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
import reactor.core.publisher.Mono;

public abstract class OrderProcessor {
    @Autowired
    private WebClient.Builder webClientBuilder;
    public void placeOrder(OrderRequestDTO newOrder, String exchange) {

        //refactor and stop creating a new application context with each call

        String response = webClientBuilder.build()
                .post()
                .uri(exchange)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(new CreateOrderOnExchangeDTO(newOrder)), CreateOrderOnExchangeDTO.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println(response);
    }
    public abstract void processOrder(OrderRequestDTO order);
}
