package org.tlc.microservices.orderservice.strategy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.microservices.orderservice.Config;
import org.tlc.microservices.orderservice.dto.CreateOrderOnExchangeDTO;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
import reactor.core.publisher.Mono;

@Component
public class DefaultOrderProcessor implements OrderProcessor {
    @Override
    public void processOrder(OrderRequestDTO newOrder) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        WebClient client = (WebClient) ctx.getBean("webClientBean");

        String response = client.post()
                .uri("https://exchange2.matraining.com/d8e53ba5-0661-4013-bf98-b950ae1f9224/order")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(new CreateOrderOnExchangeDTO(newOrder)), CreateOrderOnExchangeDTO.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println(response);
    }
}
