package org.tlc.microservices.orderservice.services.processingstrategies;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.domain.base.order.enums.TradeStatus;
import org.tlc.microservices.orderservice.dto.CreateOrderOnExchangeDTO;
import org.tlc.microservices.orderservice.dto.SaveOrderDTO;
import org.tlc.microservices.orderservice.dto.SaveTradeDTO;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.UUID;


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

    public SaveTradeDTO placeOrder(SaveOrderDTO newOrder, String key) {
        String exchangeResponse = webClientBuilder.build()
                .post()
                .uri(exchanges.get(key) + apiKey + "/order")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(new CreateOrderOnExchangeDTO(newOrder)), CreateOrderOnExchangeDTO.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();
//        UUID tradeID = UUID.fromString(exchangeResponse);
        System.out.println(exchangeResponse);

        return new SaveTradeDTO.SaveTradeDTOBuilder(exchangeResponse,
                newOrder.getOrderID(),
                TradeStatus.OPEN,
                newOrder.getQuantity(),
                newOrder.getSide(),
                exchanges.get(key),
                newOrder.getPrice())
                .build();

    }



    public abstract SaveTradeDTO processOrder(SaveOrderDTO order);


}
