package org.tlc.microservices.orderservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.microservices.orderservice.service.DefaultOrderProcessor;
import org.tlc.microservices.orderservice.service.OrderProcessor;

@Configuration
public class Config {

    @Bean
    public OrderProcessor processorBean() {
        return new DefaultOrderProcessor();
    }

    @Bean
    public WebClient webClientBean() {
        return WebClient.create();
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
