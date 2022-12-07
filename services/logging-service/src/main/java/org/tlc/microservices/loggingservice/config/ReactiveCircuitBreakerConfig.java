package org.tlc.microservices.loggingservice.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ReactiveCircuitBreakerConfig {

    @Bean("BreakerConfig")
    public CircuitBreakerConfig circuitBreakerConfig(){
        return CircuitBreakerConfig.custom()
                .slidingWindowSize(3)
                .failureRateThreshold(50)
                .minimumNumberOfCalls(5)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .waitDurationInOpenState(Duration.ofSeconds(5))
                .permittedNumberOfCallsInHalfOpenState(3)
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .build();
    }

    @Bean("RCBFactory")
    public ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory(
            @Qualifier("BreakerConfig") CircuitBreakerConfig config
    ){
        return new ReactiveResilience4JCircuitBreakerFactory(
                CircuitBreakerRegistry.of(config),
                TimeLimiterRegistry.ofDefaults()
        );
    }

    @Bean
    public ReactiveCircuitBreaker interServiceCircuitBreaker(
            @Qualifier("RCBFactory") ReactiveCircuitBreakerFactory factory
    ) {
        return factory.create("inter-service");
    }

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> customerServiceCusomtizer() {
        return factory -> {
            factory.configure(builder -> builder
                    .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build())
                    .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults()), "inter-service");
        };
    }

}
