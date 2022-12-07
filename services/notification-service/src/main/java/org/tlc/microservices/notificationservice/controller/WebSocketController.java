package org.tlc.microservices.notificationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;
import org.tlc.microservices.notificationservice.model.WebSocketContent;
import org.tlc.microservices.notificationservice.model.WebSocketMessage;
import org.tlc.microservices.notificationservice.service.RedisMessageSubscriber;

import java.util.List;


@RestController
public class WebSocketController {

    @Autowired
    RedisMessageSubscriber redisMessageSubscriber;

    @MessageMapping("/tickers")  //send data from frontend /app/tickers
    @SendTo("/topic/exchangeTicker")  //endpoint to subscribe to
    public WebSocketContent getContent(WebSocketMessage webSocketMessage) throws Exception{
        System.out.println("welcome"+ HtmlUtils.htmlEscape(webSocketMessage.getTicker()));
        return new WebSocketContent("welcome"+ HtmlUtils.htmlEscape(webSocketMessage.getTicker()));
    }

    @GetMapping("/getMessage")
    public List<String> message(){
        return redisMessageSubscriber.getReportingData();
    }
}
