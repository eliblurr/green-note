package org.tlc.microservices.marketdataservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/newOrder")
public class WebHookController {
    public static List<Object> marketDataOutput = new ArrayList<>();

    public Object newObject;
    public String getNewObject(){
        return this.newObject.toString();
    }

    @PostMapping
    public void getMarketData(@RequestBody Object newData){
        marketDataOutput.add(newData);   //add incoming orders to the list
        this.newObject=newData;
        System.out.println("Data came in"+ newData);
    }
}
