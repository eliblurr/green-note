package org.tlc.domain.base.marketData;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

@Component
public class OrderingServiceDto {
    private String exchangeName;
    private Map<String, HashMap<String, Queue<Double>>> tickers = new HashMap<>();

    public OrderingServiceDto() {
    }

    public OrderingServiceDto(String exchangeName, HashMap<String, HashMap<String, Queue<Double>>> tickers) {
        this.exchangeName = exchangeName;
        this.tickers = tickers;
    }

    public Boolean tickerExist(String ticker){
        return tickers.containsKey(ticker);
    }

    public Boolean reachedMaxPriceList(Queue<Double> priceArray){
        return priceArray.size()>9;
    }

    public void AddTickerPrices(String ticker, Double price, String side){
        //add a ticker for the first time
        if (!tickerExist(ticker)){
            tickers.put(ticker, new HashMap<>());
        }

        //add side for the first time
        if (!tickers.get(ticker).containsKey(side)){
            tickers.get(ticker).put(side,new LinkedList<>());
        }

        //add price to the corresponding ticker and side
        if (!reachedMaxPriceList(tickers.get(ticker).get(side))){
            tickers.get(ticker).get(side).add(price);
        }

        else{
            tickers.get(ticker).get(side).remove();
            tickers.get(ticker).get(side).add(price);
        }

    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    @Override
    public String toString() {
        return "OrderingServiceDto{" +
                "exchangeName='" + exchangeName + '\'' +
                ", tickers=" + tickers +
                '}';
    }
}
