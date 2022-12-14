package org.tlc.microservices.orderservice.services.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.domain.base.dto.ClientValidationDTO;
import org.tlc.domain.base.order.dto.ValidateCustomerDTO;
import reactor.core.publisher.Mono;

@Service
public class ClientDataFetcher {
    @Autowired
    WebClient.Builder webClientBuilder;
    public ClientValidationDTO getUserData(ValidateCustomerDTO customerInfo) {
        return webClientBuilder.build()
                .post()
                .uri("http://user-service/api/users/customers/validate-customer")
                .body(Mono.just(customerInfo),ValidateCustomerDTO.class)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ClientValidationDTO.class)
                .block();
    }
}
