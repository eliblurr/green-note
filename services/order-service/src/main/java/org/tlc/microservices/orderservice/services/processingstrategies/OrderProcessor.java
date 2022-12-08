package org.tlc.microservices.orderservice.services.processingstrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.microservices.orderservice.configuration.WebClientConfiguration;
import org.tlc.microservices.orderservice.dto.CreateOrderOnExchangeDTO;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public abstract class OrderProcessor {
    @Autowired
    private WebClient.Builder webClientBuilder;
    private HashMap<String, String> exchanges = new HashMap<>();
    @Value("${environment.apikey}")
    private String apiKey;

    public OrderProcessor() {
        this.exchanges.put("MAL1", "https://exchange.matraining.com/");
        this.exchanges.put("MAL2", "https://exchange2.matraining.com/");
    }

    public void placeOrder(OrderRequestDTO newOrder, String key) {
        String response = webClientBuilder.build()
                .post()
                .uri(exchanges.get(key) + apiKey + "/order")
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
