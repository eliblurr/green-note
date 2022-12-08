package org.tlc.microservices.marketdataservice.controller;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.domain.base.marketData.OrderingServiceDto;
import org.tlc.domain.base.marketData.ReportingServiceDto;
import org.tlc.microservices.marketdataservice.dto.ExchangeProducts;
import org.tlc.microservices.marketdataservice.service.KafkaPublish;


import java.text.ParseException;


@RestController
@RequestMapping("api/WebHook/newOrder")
public class WebHookController {
    @Autowired
    WebClient.Builder webClientBuilder;

    OrderingServiceDto orderingServiceDto = new OrderingServiceDto();

    @Autowired
    private KafkaPublish KafkaPublish;

    @Autowired @Qualifier("reportTopic") private NewTopic topic;

    public WebHookController() {
    }

    /**
     *
     * @return web client builder
     * get market data market information
     */
    @GetMapping("/GetExchangeProduct")
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
     * listen to the exchange using webhook and immediately publish on redis
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
//        KafkaPublish.sendMessage(orderingServiceDto);

    }




}
