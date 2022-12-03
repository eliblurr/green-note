package org.tlc.microservices.marketdataservice.controller;

import org.springframework.web.bind.annotation.*;
import org.tlc.microservices.marketdataservice.model.RedisMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/WebHook/newOrder")
public class WebHookController {
    public static List<RedisMessage> marketDataOutput = new ArrayList<>();

    @PostMapping
    public void marketData(@RequestBody Object newData){
        List<String> received= Stream.of(newData.toString())
                .map(field -> field.split(","))
                .flatMap(Arrays::stream)
                .flatMap(Pattern.compile("=")::splitAsStream)
                .collect(Collectors.toList());

        RedisMessage redisMessage = new RedisMessage(received.get(3),Double.parseDouble(received.get(9)),received.get(1));
        marketDataOutput.add(redisMessage);
        System.out.println(redisMessage);
    }

    @GetMapping
    public List<RedisMessage> getMarketData(){
        return marketDataOutput;
    }
}
