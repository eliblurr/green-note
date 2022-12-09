package org.tlc.microservices.marketdataservice.controller;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.domain.base.marketData.OrderingServiceDto;
import org.tlc.domain.base.marketData.ReportingServiceDto;
import org.tlc.microservices.marketdataservice.dto.ExchangeProducts;
import org.tlc.microservices.marketdataservice.model.GetExchangePd;
import org.tlc.microservices.marketdataservice.service.KafkaPublish;
import org.tlc.microservices.marketdataservice.service.OrderingServicePublisher;

import java.text.ParseException;

@RestController
@EnableCaching

@RequestMapping("api/WebHook/newOrder")
public class WebHookController {
    @Autowired
    WebClient.Builder webClientBuilder;

    @Autowired
    OrderingServiceDto orderingServiceDto;

    @Autowired
    private KafkaPublish KafkaPublish;
    @Autowired
    private OrderingServicePublisher orderingServicePublisher;

    @Autowired
    private GetExchangePd getExchangePd;

    @Autowired @Qualifier("reportTopic") private NewTopic topic;
    @Autowired @Qualifier("orderTopic") private NewTopic topic2;

    public WebHookController() {
    }

    /**
     *
     * @return web client builder
     * get market data market information
     */
    @GetMapping("/GetExchangeProduct")
    @Cacheable
    public ExchangeProducts[] getExchangeProduct(){
        return webClientBuilder.build()
                .get()
                .uri("https://exchange.matraining.com/pd")
                .retrieve()
                .bodyToMono(ExchangeProducts[].class)
                .block();
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
        orderingServiceDto.setExchangeName(newData.getExchange());
        orderingServiceDto.AddTickerPrices(newData.getProduct(),newData.getPrice(),newData.getSide());
        System.out.println("ordering service: "+orderingServiceDto.toString());
        orderingServicePublisher.setTopic(topic2);
        System.out.println(topic2.name());
        orderingServicePublisher.sendMessage(orderingServiceDto);
    }




}
