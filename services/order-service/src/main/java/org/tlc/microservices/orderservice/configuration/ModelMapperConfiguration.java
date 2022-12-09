package org.tlc.microservices.orderservice.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ModelMapperConfiguration {
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

}
