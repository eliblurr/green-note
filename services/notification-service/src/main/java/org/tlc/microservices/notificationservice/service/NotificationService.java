package org.tlc.microservices.notificationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.data.redis.connection.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class NotificationService {

    @Autowired private SimpMessagingTemplate simpMessagingTemplate;
    public void sendMessage(String message){
        simpMessagingTemplate.convertAndSend("/topic/tickers", message);
    }

}
