package org.tlc.microservices.marketdataservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tlc.microservices.marketdataservice.model.RedisMessage;
import org.tlc.microservices.marketdataservice.service.RedisMessagePublish;

@RestController
@RequestMapping("/api/redis")
@EnableCaching
public class RedisController {

    private static Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisMessagePublish messagePublish;
    @Autowired
    private WebHookController webHookController;

    @PostMapping("/publish")
    public void publish(@RequestBody RedisMessage message) {
        logger.info(">> publishing : {}", message);
        messagePublish.publish(message.toString());
//        System.out.println("webhookData:" + webHookController.getNewObject());
    }
}


