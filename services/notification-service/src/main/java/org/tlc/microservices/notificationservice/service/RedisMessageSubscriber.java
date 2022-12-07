package org.tlc.microservices.notificationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.processing.Messager;
import java.util.ArrayList;
import java.util.List;

@Service
public class RedisMessageSubscriber implements MessageListener {
    @Autowired
    WebClient.Builder webClientBuilder;
    public static List<String> messageList = new ArrayList<>();
    private String newMessage;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        this.newMessage = message.toString();
        messageList.add(message.toString());
        System.out.println("Message received : "+message);

//        send the message to the subsc

        /*webClientBuilder.baseUrl("http://localhost:8081/app/tickers")
                .defaultHeader("Content-Type", "application/json").build()
                .post().body(Mono.just(message), Message.class)
                .retrieve().bodyToFlux(Message.class).subscribe(
                        confirmation-> {System.out.println("Message Stored!");},
                        error->System.out.println(error)
                );*/
    }

    public String getNewData(){
        return this.newMessage;
    }

    public List<String> getReportingData(){
        return messageList;
    }
}
