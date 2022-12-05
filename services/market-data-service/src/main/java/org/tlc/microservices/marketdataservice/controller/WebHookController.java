package org.tlc.microservices.marketdataservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.microservices.marketdataservice.dto.ExchangeProducts;
import org.tlc.microservices.marketdataservice.dto.ReportingServiceDto;
import reactor.core.publisher.Mono;

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
    public void marketData(@RequestBody ReportingServiceDto newData){
        //create a webclient to publish message on the redis server
        webClientBuilder.baseUrl("http://localhost:8080/api/redis")
                .defaultHeader("Content-Type", "application/json").build()
                .post().uri("/publish").body(Mono.just(newData), ReportingServiceDto.class)
                .retrieve().bodyToFlux(ReportingServiceDto.class).subscribe(
                confirmation-> {System.out.println("Message Stored!");},
                error->System.out.println(error)
        );

    }

}
