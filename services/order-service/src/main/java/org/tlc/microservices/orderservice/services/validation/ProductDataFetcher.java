package org.tlc.microservices.orderservice.services.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.microservices.orderservice.configuration.dto.ProductDataDTO;

@Service
public class ProductDataFetcher {
    @Autowired
    WebClient.Builder webClientBuilder;
    public ProductDataDTO getProductData(String product) {
        return webClientBuilder.build()
                .get()
                .uri("https://exchange.matraining.com/pd/" + product)
                .retrieve()
                .bodyToMono(ProductDataDTO.class)
                .block();
    }
}
