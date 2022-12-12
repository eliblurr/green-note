package org.tlc.domain.base.marketData;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Data
@Setter
@Getter
public class OrderingServiceDto {
    private Map[] ExchangeList = new Map[2];
    private Map<String, HashMap<String, Queue<TickerPriceDto>>> tickers = new HashMap<>();

    public OrderingServiceDto() {
    }

    public OrderingServiceDto(HashMap<String, HashMap<String, Queue<TickerPriceDto>>> tickers) {
        this.tickers = tickers;
    }

    /**
     *
     * @param ticker
     * @return
     * check if a ticker exists
     */

    public Boolean tickerExist(String ticker){
        return tickers.containsKey(ticker);
    }

    /**
     *
     * @param priceArray
     * @return Boolean
     * mantaining 10 recent records for each ticker in each exchange
     */
    public Boolean reachedMaxPriceList(Queue<TickerPriceDto> priceArray){
        return priceArray.size()>9;
    }

    /**
     *
     * @param exchange
     * @param ticker
     * Add hashmap of tickers to either mal1 OR mal2
     */
    public void addTickerExchange(String exchange,Map<String, HashMap<String, Queue<TickerPriceDto>>> ticker){
        if (exchange=="MAL1"){
            ExchangeList[0]=ticker;
        }
        else{
            ExchangeList[1] = ticker;
        }
    }

    /**
     *
     * @param exchange
     * @param ticker
     * @param price
     * @param side
     * ADD incoming  trades in a queue and pop the old one
     */

    public void AddTickerPrices(String exchange,String ticker, TickerPriceDto price, String side){
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
        this.addTickerExchange(exchange,tickers);
    }


    @Override
    public String toString() {
        return "OrderingServiceDto{" +
                "Exchange List=" + Arrays.toString(ExchangeList) +
                '}';
    }
}
