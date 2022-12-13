package org.tlc.microservices.orderservice.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Bean
    @Primary
    @LoadBalanced
    public WebClient.Builder getWebClientBuilder(){
        return WebClient.builder();
    }

    @Bean("externalWebClient")
//    @Qualifier("externalWebClient")
    public WebClient.Builder getExternalWebClientBuilder(){
        return WebClient.builder();
    }

}
