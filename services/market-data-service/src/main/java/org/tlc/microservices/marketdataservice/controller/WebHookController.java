package org.tlc.microservices.marketdataservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.microservices.marketdataservice.dto.ExchangeProducts;
import org.tlc.microservices.marketdataservice.model.RedisMessage;
import reactor.core.publisher.Mono;


import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/WebHook/newOrder")
public class WebHookController {
    @Autowired
    WebClient.Builder webClientBuilder;

    @GetMapping("/GetExchangeProduct")
    public ExchangeProducts[] getExchangeProduct(){
        return webClientBuilder.build()
                .get()
                .uri("https://exchange.matraining.com/pd")
                .retrieve()
                .bodyToMono(ExchangeProducts[].class)
                .block();
    }

    @PostMapping
    public void marketData(@RequestBody Object newData){

        //split the object into different fields
        List<String> received= Stream.of(newData.toString())
                .map(field -> field.split(","))
                .flatMap(Arrays::stream)
                .flatMap(Pattern.compile("=")::splitAsStream)
                .collect(Collectors.toList());
        RedisMessage redisMessage = new RedisMessage(received.get(3),Double.parseDouble(received.get(9)),received.get(1));

        //create a webclient to publish message on the redis server
        webClientBuilder.baseUrl("http://localhost:8080/api/redis")
                .defaultHeader("Content-Type", "application/json").build()
                .post().uri("/publish").body(Mono.just(redisMessage), RedisMessage.class)
                .retrieve().bodyToFlux(RedisMessage.class).subscribe(
                confirmation-> {System.out.println("Message Stored!");},
                error->System.out.println(error)
        );

    }

}
