package org.tlc.microservices.orderservice.services.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.microservices.orderservice.dto.ProductDataDTO;

@Service
public class ProductDataFetcher {
    @Autowired @Qualifier("externalWebClient")
    WebClient.Builder webClientBuilder;
    public ProductDataDTO getProductData(String product) {
        System.out.println("Product data is being fetched*****************************************");
        return webClientBuilder.build()
                .get()
                .uri("https://exchange.matraining.com/pd/" + product)
                .retrieve()
                .bodyToMono(ProductDataDTO.class)
                .block();
    }
}
