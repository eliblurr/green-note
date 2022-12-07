package org.tlc.microservices.notificationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.tlc.microservices.notificationservice.model.WebSocketContent;
import org.tlc.microservices.notificationservice.service.RedisMessageSubscriber;

import java.util.List;

@RestController
public class WebSocketController {

    @Autowired
    RedisMessageSubscriber redisMessageSubscriber;

    @MessageMapping("/tickers")  //send data from frontend /app/tickers
    @SendTo("/topic/exchangeTicker")  //endpoint to subscribe to
    @CrossOrigin("http://localhost:4200")
    public WebSocketContent getContent() throws Exception{
        System.out.println("welcome:\n"+ redisMessageSubscriber.getNewData()+"\n");
        return new WebSocketContent("welcome"+ redisMessageSubscriber.getNewData());
    }

    @GetMapping("/getMessage")
    public List<String> message(){
        return redisMessageSubscriber.getReportingData();
    }
}
