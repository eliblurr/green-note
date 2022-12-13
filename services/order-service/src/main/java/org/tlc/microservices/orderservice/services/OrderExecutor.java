package org.tlc.microservices.orderservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.domain.base.order.enums.LegStatus;
import org.tlc.microservices.orderservice.configuration.ExchangesConfig;
import org.tlc.microservices.orderservice.dto.CreateOrderOnExchangeDTO;
import org.tlc.domain.base.order.dto.SaveOrderDTO;
import org.tlc.microservices.orderservice.dto.SaveTradeDTO;
import reactor.core.publisher.Mono;

@Component
public class OrderExecutor {
    @Autowired @Qualifier("externalWebClient")
    private WebClient.Builder webClientBuilder;

    @Value("${environment.apikey}")
    private String apiKey;

    @Autowired
    ExchangesConfig exchanges;

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

        return new SaveTradeDTO.SaveTradeDTOBuilder(exchangeResponse,
                newOrder.getOrderId(),
                LegStatus.OPEN,
                newOrder.getQuantity(),
                newOrder.getSide(),
                exchanges.get(key),
                newOrder.getPrice())
                .build();
    }

}

