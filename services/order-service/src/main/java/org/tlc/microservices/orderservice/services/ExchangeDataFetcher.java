package org.tlc.microservices.orderservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.domain.base.marketData.ExchangesDTO;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ExchangeDataFetcher {
    @Autowired
    WebClient.Builder webClientBuilder;
    public List<ExchangesDTO> getExchanges() {
        return Arrays.asList(
                Objects.requireNonNull(webClientBuilder.build()
                        .get()
                        .uri("http://market-data-service/api/WebHook/newOrder/getExchanges")
                        .retrieve()
                        .bodyToMono(ExchangesDTO[].class)
                        .block()));
    }

}
