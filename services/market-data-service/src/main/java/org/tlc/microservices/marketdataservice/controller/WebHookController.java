package org.tlc.microservices.marketdataservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.microservices.marketdataservice.dto.ExchangeProducts;
import org.tlc.microservices.marketdataservice.dto.ReportingServiceDto;
import org.tlc.microservices.marketdataservice.dto.TickerDto;
import org.tlc.microservices.marketdataservice.model.Ticker;
import org.tlc.microservices.marketdataservice.service.TickerService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/WebHook/newOrder")
public class WebHookController {
    @Autowired
    WebClient.Builder webClientBuilder;

    @Autowired
    TickerService tickerService;

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

        //save data to mongo Db
        TickerDto tickerDto = new TickerDto(newData);
        Ticker ticker = tickerDto.convertToEntity();
        tickerService.AddTicker(ticker);
        System.out.println("saved ticker: "+ ticker );

    }
}
