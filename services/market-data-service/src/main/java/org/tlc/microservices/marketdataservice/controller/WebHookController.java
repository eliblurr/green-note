package org.tlc.microservices.marketdataservice.controller;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.domain.base.marketData.OrderingServiceDto;
import org.tlc.domain.base.marketData.ReportingServiceDto;
import org.tlc.domain.base.marketData.TickerPriceDto;
import org.tlc.microservices.marketdataservice.service.KafkaPublish;
import org.tlc.microservices.marketdataservice.service.OrderingServicePublisher;
import reactor.core.publisher.Mono;

import java.text.ParseException;


@RestController
@EnableCaching
@RequestMapping("api/WebHook/newOrder")
public class WebHookController {
    @Autowired WebClient.Builder webClientBuilder;

    @Autowired OrderingServiceDto orderingServiceDto;

    @Autowired private KafkaPublish KafkaPublish;
    @Autowired private OrderingServicePublisher orderingServicePublisher;

    @Autowired @Qualifier("reportTopic") private NewTopic topic;
    @Autowired @Qualifier("orderTopic") private NewTopic topic2;

    private final String webHookUrl = "https://d9e0-102-22-11-130.ngrok.io/api/WebHook/newOrder";

    public WebHookController() {
    }

    /**
     *
     * subscribes to exchange1 and exchange2
     */
    @PostMapping("/Subscribe")
    public void exchangeSubscribe(){
        webClientBuilder.build()
                .post().uri("https://exchange.matraining.com/pd/subscription").body(Mono.just(webHookUrl), String.class)
                .retrieve().bodyToFlux(String.class).subscribe(
                        confirmation-> {System.out.println("MAL1 Subscribed succesfully");},
                        System.out::println
                );

        webClientBuilder.build()
                .post().uri("https://exchange2.matraining.com/pd/subscription").body(Mono.just(webHookUrl), String.class)
                .retrieve().bodyToFlux(String.class).subscribe(
                        confirmation-> {System.out.println("MAL2 Subscribed succesfully");},
                        System.out::println
                );
    }

    /**
     *
     * @param newData returns an Object
     * @throws ParseException
     * listen to the exchange using webhook and immediately publish on kafka
     */
    @PostMapping
    public void marketData(@RequestBody ReportingServiceDto newData) {
        System.out.println("new data came in: "+ newData.toString());

        //publish to front end and reporting
        KafkaPublish.setTopic(topic);
        System.out.println(topic.name());
        KafkaPublish.sendMessage(newData);

        //publish to ordering service
        TickerPriceDto tickerPriceDto = new TickerPriceDto(newData.getPrice(),newData.getQty());
        orderingServiceDto.AddTickerPrices(newData.getExchange(),newData.getProduct(),tickerPriceDto,newData.getSide());
        System.out.println("ordering service: "+orderingServiceDto.toString());
        orderingServicePublisher.setTopic(topic2);
        System.out.println(topic2.name());
        orderingServicePublisher.sendMessage(orderingServiceDto);
    }
}
