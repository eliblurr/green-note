package org.tlc.microservices.marketdataservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.tlc.microservices.marketdataservice.model.TradeNotification;
import org.tlc.microservices.marketdataservice.repository.TradeNotificationRepository;

import java.util.List;

@Service
public class TradeNotificationService {
    @Autowired
    private TradeNotificationRepository tickerRepository;
    //update trades function
    public TradeNotification saveTrade(TradeNotification tradeNotification){
        return tickerRepository.saveTicker(tradeNotification);
    }


    public List<Object> getAllTickers(){
        return tickerRepository.findAll();
    }

    public Object getTrade(@PathVariable String tradeId){
        return tickerRepository.findByTradeId(tradeId);
    }

    public Boolean tradeExist(@PathVariable String tradeId){
        return tickerRepository.tradeExist(tradeId);
    }


}

