package org.tlc.microservices.orderservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.domain.base.dto.ClientValidationDTO;
import org.tlc.domain.base.order.dto.ValidateCustomerDTO;
import reactor.core.publisher.Mono;

public class ExchangeDataFetcher {

    @Autowired
    WebClient.Builder webClientBuilder;
    public List<Exchange> getExchanges() {
        return webClientBuilder.build()
                .get()
                .uri("http://market-data-service/api/WebHook/newOrder/getExchanges")
                .retrieve()
                .bodyToMono(Exchange[].class)
                .block();
    }

}
