package org.tlc.microservices.marketdataservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/newOrder")
public class WebHookController {
    public static List<Object> marketDataOutput = new ArrayList<>();

    @PostMapping
    public void getMarketData(@RequestBody Object newData){
        marketDataOutput.add(newData);   //add incoming orders to the list
        System.out.println("Data came in"+ newData);
    }
}
