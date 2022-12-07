package org.tlc.microservices.orderservice.services.processingstrategies;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.microservices.orderservice.Config;
import org.tlc.microservices.orderservice.dto.CreateOrderOnExchangeDTO;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
import reactor.core.publisher.Mono;

public abstract class OrderProcessor {
    public void placeOrder(OrderRequestDTO newOrder, String exchange) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        WebClient client = (WebClient) ctx.getBean("webClientBean");
        //refactor and stop creating a new application context with each call

        String response = client.post()
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
