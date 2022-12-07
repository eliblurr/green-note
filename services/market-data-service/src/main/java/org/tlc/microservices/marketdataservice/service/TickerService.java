package org.tlc.microservices.marketdataservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

@Service
public class TickerService {
    private HashMap<String, Queue<Double>> tickers = new HashMap<>();

    public Boolean tickerExist(String ticker){
        return tickers.containsKey(ticker);
    }

    public Boolean reachedMaxPriceList(Queue<Double> priceArray){
        return priceArray.size()>9;
    }

    public void AddTickerPrices(String ticker, Double price){
        if (!tickerExist(ticker)){
            tickers.put(ticker, new LinkedList<>());
        }

        if (!reachedMaxPriceList(tickers.get(ticker))){
            tickers.get(ticker).add(price);
        }

        else{
            tickers.get(ticker).remove();
            tickers.get(ticker).add(price);
        }

    }

    @Override
    public String toString() {
        return "TickerService{" +
                "tickers=" + tickers.toString() +
                '}';
    }
}
