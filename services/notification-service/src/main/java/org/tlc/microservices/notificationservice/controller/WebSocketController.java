package org.tlc.microservices.notificationservice.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;
import org.tlc.microservices.notificationservice.model.WebSocketContent;
import org.tlc.microservices.notificationservice.model.WebSocketMessage;

@RestController
public class WebSocketController {

    @MessageMapping("/tickers")  //send data from frontend /app/tickers
    @SendTo("/topic/exchangeTicker")  //endpoint to subscribe to
    public WebSocketContent getContent(WebSocketMessage webSocketMessage) throws Exception{
        System.out.println("welcome"+ HtmlUtils.htmlEscape(webSocketMessage.getTicker()));
        return new WebSocketContent("welcome"+ HtmlUtils.htmlEscape(webSocketMessage.getTicker()));
    }
}
