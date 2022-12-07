package org.tlc.microservices.orderservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Bean
    public WebClient webClientBean() {
        return WebClient.create();
    }


/*    @Bean
    public WebClient getWebClientToExchange()
    {
        //build webclient to connect to exchange
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }*/
}
