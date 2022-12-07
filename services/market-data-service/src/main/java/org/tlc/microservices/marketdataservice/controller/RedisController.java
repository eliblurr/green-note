package org.tlc.microservices.marketdataservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.tlc.microservices.marketdataservice.dto.ReportingServiceDto;
import org.tlc.microservices.marketdataservice.service.RedisMessagePublish;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/redis")
@EnableCaching
public class RedisController {
    private static Logger logger = LoggerFactory.getLogger(RedisController.class);
    @Autowired
    private RedisMessagePublish messagePublish;

    private List<ReportingServiceDto> repository = new ArrayList<>();

    @PostMapping("/publish")
    public void publish(@RequestBody ReportingServiceDto redisMessage) {
        messagePublish.publish(redisMessage.toString());
        this.repository.add(redisMessage);

        logger.info(">> publishing : {}", redisMessage);
    }

//    @PostMapping("/PricesPublish")
//    public void notificationPublish(@RequestBody ReportingServiceDto redisMessage) {
//        messagePublish.publish(redisMessage.toString());
//        this.repository.add(redisMessage);
//
//        logger.info(">> publishing : {}", redisMessage);
//    }
}


