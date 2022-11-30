package org.tlc.microservices.orderservice.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.microservices.orderservice.Config;
import org.tlc.microservices.orderservice.dto.Order;
import org.tlc.microservices.orderservice.dto.OrderRequest;
import org.tlc.microservices.orderservice.dto.Response;
import reactor.core.publisher.Mono;

import java.net.URI;

public class DefaultOrderProcessor implements OrderProcessor {
    @Override
    public Response processOrder(OrderRequest newOrder) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        WebClient client = (WebClient) ctx.getBean("webClientBean");

        String response = client.post()
                .uri("https://exchange.matraining.com/d8e53ba5-0661-4013-bf98-b950ae1f9224/order")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(new Order(newOrder.getProduct(), newOrder.getQuantity(),
                        newOrder.getPrice(), newOrder.getSide(), newOrder.getType())), Order.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return new Response(true, response);
    }
}
