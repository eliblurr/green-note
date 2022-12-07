package org.tlc.microservices.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    @Order(1)
    public SecurityWebFilterChain disableCSRFFilterChain(ServerHttpSecurity serverHttpSecurity){
        return serverHttpSecurity.csrf().disable().build();
    }

    @Bean
    @Order(2)
    public SecurityWebFilterChain methodFilterChain(ServerHttpSecurity serverHttpSecurity){
        return serverHttpSecurity.authorizeExchange(
                exchange -> exchange.anyExchange().permitAll()
        ).build();
    }

//    @Bean
//    @Order(2)
//    public SecurityWebFilterChain methodFilterChain(ServerHttpSecurity serverHttpSecurity){
//        return serverHttpSecurity.authorizeExchange(exchange -> exchange
//                .pathMatchers(HttpMethod.POST,
//                        "/api/users/admins", "/api/users/admins/",
//                        "/api/users/customers", "/api/users/customers/",
//                        "/api/users/authenticate/**", "/api/users/authenticate"
//                ).permitAll()
//                .pathMatchers(HttpMethod.GET,
//                        "/eureka/**",
//                        "/api/users/admins/",
//                        "/api/users/admins"
//                )
//                .permitAll()
//        ).build();
//    }

    @Bean
    @Order(3)
    public SecurityWebFilterChain authFilterChain(ServerHttpSecurity serverHttpSecurity){
        return serverHttpSecurity.csrf().disable().authorizeExchange(exchange -> exchange
                .anyExchange().authenticated()
        ).build();
    }

}
