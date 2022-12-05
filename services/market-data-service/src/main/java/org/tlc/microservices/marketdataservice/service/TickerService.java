package org.tlc.microservices.marketdataservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tlc.microservices.marketdataservice.model.Ticker;
import org.tlc.microservices.marketdataservice.repository.TickerRepository;

@Service
public class TickerService {
    @Autowired
    TickerRepository tickerRepository;

    public void AddTicker(Ticker ticker){
        tickerRepository.save(ticker);
    }
}
