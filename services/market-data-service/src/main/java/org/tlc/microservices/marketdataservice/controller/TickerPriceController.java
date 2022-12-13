package org.tlc.microservices.marketdataservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tlc.microservices.marketdataservice.model.TickerPrice;
import org.tlc.microservices.marketdataservice.repository.TickerPriceRepository;

import java.util.List;

@RestController
public class TickerPriceController {
    @Autowired
    private TickerPriceRepository tickerRepository;
    @PostMapping("/TickerPrices")
    @CachePut(value = "TickerPrice")
    //update price function
//    @CacheEvict(value = "TickerPrice",allEntries = false,key = "#ticker.id")
    public TickerPrice saveTickers(){
        return tickerRepository.saveTicker(new TickerPrice(1L, "username2", "emailid2","SELL",3.9));
    }

    @PostMapping("/NewTickerPrices")
    @CachePut(value = "TickerPrice")
    //update price function
//    @CacheEvict(value = "TickerPrice",allEntries = false,key = "#ticker.id")
    public TickerPrice saveNewTickers(){
        return tickerRepository.removeOutdatedTickers(new TickerPrice(2L, "username3", "emailid3","BUY",9.9));
    }
    @GetMapping("/getPrices")
    @Cacheable(value = "TickerPrice[]")
    public List<Object> getAllTickers(){
        return tickerRepository.findAll();
    }

    @GetMapping("/getTicker/{tickerName}")
    @Cacheable(key = "#tickerName",value = "TickerPrice")
    public Object getTicker(@PathVariable String tickerName){
        return tickerRepository.findByTickerName(tickerName);
    }
}

