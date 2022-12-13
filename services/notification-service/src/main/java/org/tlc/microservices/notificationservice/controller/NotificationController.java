package org.tlc.microservices.notificationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tlc.microservices.notificationservice.service.NotificationService;

@RestController
@RequestMapping("/api/tests/socket")
@CrossOrigin(origins = {"http://127.0.0.1:*", "http://localhost:*"})
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping(value = {"/"})
    @ResponseStatus(HttpStatus.OK)
    void read(){
        System.out.println("\n\n[TestController]I was hit\n\n");
//        notificationService.sendMessage("message sent");
    }

}












//    @Autowired
//    RedisMessageSubscriber redisMessageSubscriber;

//    @MessageMapping("/tickers")  //send data from frontend /app/tickers
//    @SendTo("/topic/exchangeTicker")  //endpoint to subscribe to
//    @CrossOrigin("http://localhost:*")
//    public WebSocketContent getContent() throws Exception{
//        System.out.println("[SOCKET]received message");
//        System.out.println("welcome:\n"+ redisMessageSubscriber.getNewData()+"\n");
//        return new WebSocketContent("welcome"+ redisMessageSubscriber.getNewData());
//    }

//    @GetMapping("/getMessage")
//    public List<String> message(){
//        return redisMessageSubscriber.getReportingData();
//    }

//    @Autowired
//    private SimpMessagingTemplate simpMessagingTemplate;
//
//    @MessageMapping("/news")
//    public void broadcastNews(String message) {
//        this.simpMessagingTemplate.convertAndSend("/topic/exchangeTicker", message);
//    }

