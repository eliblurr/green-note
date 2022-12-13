package org.tlc.microservices.orderservice.services.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.domain.base.dto.ClientValidationDTO;
import org.tlc.domain.base.order.dto.ValidateCustomerDTO;

@Service
public class ClientDataFetcher {
    @Autowired
    WebClient.Builder webClientBuilder;
    public ClientValidationDTO getUserData(ValidateCustomerDTO customerInfo) {
        return webClientBuilder.build()
                .get()
                .uri("https://user-service/api/users/customersvalidate-customer" + customerInfo)
                .retrieve()
                .bodyToMono(ClientValidationDTO.class)
                .block();
    }
}
