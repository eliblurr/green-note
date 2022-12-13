package org.tlc.microservices.marketdataservice.controller;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.domain.base.marketData.OrderingServiceDto;
import org.tlc.domain.base.marketData.ReportingServiceDto;
import org.tlc.domain.base.marketData.TickerPriceDto;
import org.tlc.microservices.marketdataservice.model.Exchange;
import org.tlc.microservices.marketdataservice.model.TradeNotification;
import org.tlc.microservices.marketdataservice.service.*;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.util.List;


@RestController
@EnableCaching
@RequestMapping("api/WebHook/newOrder")
public class ExchangeDataController {
    @Autowired WebClient.Builder webClientBuilder;

    @Autowired OrderingServiceDto orderingServiceDto;

    @Autowired private KafkaPublish KafkaPublish;
    @Autowired private OrderingServicePublisher orderingServicePublisher;
    @Autowired private NotificationPublisher notificationPublisher;
    @Autowired @Qualifier("reportTopic") private NewTopic topic;
    @Autowired @Qualifier("orderTopic") private NewTopic topic2;
    @Autowired @Qualifier("tradeTopic") private NewTopic tradeTopic;

    @Value("${spring.webhook.url}")
    private String webHookUrl;

    @Autowired private TradeNotificationService tradeNotificationService;
    @Autowired private ExchangeService exchangeService;
    @Autowired private PdService pdService;

    public ExchangeDataController() {
    }

    /**
     *
     * subscribes to exchange1 and exchange2
     */
    @PostMapping("/AddExchange")
    public void exchangeSubscribe(@RequestBody Exchange exchange){
        webClientBuilder.build()
                .post().uri(exchange.getExchangeUrl()+"/pd/subscription").body(Mono.just(webHookUrl), String.class)
                .retrieve().bodyToFlux(String.class).subscribe(
                        confirmation-> {System.out.println("Exchange Subscription success");},
                        System.out::println
                );
        //add to database
        exchangeService.saveExchange(exchange);
        //call the pd of the exchange
        pdService.pd(exchange.getExchangeUrl());
    }


    /**
     *
     * @return list of available exchanges
     */
    @GetMapping("/GetExchanges")
    public List<Object> getExchanges(){
        return exchangeService.getAllExchanges();
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
        //add and update orders in redis database
        //publish orders to front end and reporting service
        TradeNotification tradeNotification = new TradeNotification(newData.getOrderID(), newData.getCumPrx(), newData.getCumQty());
        if (!tradeNotificationService.tradeExist(newData.getOrderID())){
            tradeNotificationService.saveTrade(tradeNotification);

            //publish orders on orders topic
            KafkaPublish.setTopic(topic);
            System.out.println(topic.name());
            KafkaPublish.sendMessage(newData);
        }

        //publish trades on trade topic
        else{
            notificationPublisher.setTopic(tradeTopic);
            TradeNotification tradeNotificationById = (TradeNotification) tradeNotificationService.getTrade(newData.getOrderID());
            int databasecCumQty = tradeNotificationById.getCumulatitiveQuantity();
            newData.setCumQty(newData.getCumQty()-databasecCumQty);
            System.out.println(tradeTopic.name());
            notificationPublisher.sendMessage(newData);
        }

        //publish to ordering service
        TickerPriceDto tickerPriceDto = new TickerPriceDto(newData.getPrice(),newData.getQty());
        orderingServiceDto.AddTickerPrices(newData.getExchange(),newData.getProduct(),tickerPriceDto,newData.getSide());
        System.out.println("ordering service: "+orderingServiceDto.toString());
        orderingServicePublisher.setTopic(topic2);
        System.out.println(topic2.name());
        orderingServicePublisher.sendMessage(orderingServiceDto);
    }
}
