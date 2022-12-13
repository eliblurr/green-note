package org.tlc.microservices.marketdataservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.tlc.microservices.marketdataservice.model.Exchange;
import org.tlc.microservices.marketdataservice.model.TradeNotification;
import org.tlc.microservices.marketdataservice.repository.ExchangeRepo;
import org.tlc.microservices.marketdataservice.repository.TradeNotificationRepository;

import java.util.List;

@Service
public class ExchangeService {
    @Autowired
    private ExchangeRepo exchangeRepo;

    public Exchange saveExchange(Exchange exchange){
        return exchangeRepo.saveExchange(exchange);
    }

    public List<Object> getAllExchanges(){
        return exchangeRepo.findAllExchanges();
    }
}
