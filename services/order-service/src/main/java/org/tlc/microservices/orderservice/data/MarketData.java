package org.tlc.microservices.orderservice.data;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.tlc.domain.base.marketData.OrderingServiceDto;
import org.tlc.domain.base.marketData.TickerPriceDto;
import org.tlc.domain.base.order.enums.Side;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
@Data
public class MarketData {
    private OrderingServiceDto marketData;


    private List<Map<String, HashMap<String, Queue<TickerPriceDto>>>> exchangeDataList = new ArrayList<>();
//    private Map<String, HashMap<String, Queue<TickerPriceDto>>> tickers = new HashMap<>();

    public List<TickerPriceDto> getLastTenPrices(String exchangeKey, String product, Side side) {
        if (side.equals(Side.BUY)) {
            return marketData.getTickers()
                    .get(product)
                    .get(side.name())
                    .stream()
                    .sorted(Comparator.comparing(TickerPriceDto::getPrice))
                    .collect(Collectors.toList());
        }
        if (side.equals(Side.SELL)) {
            return marketData.getTickers()
                    .get(product)
                    .get(side.name())
                    .stream()
                    .sorted(Comparator.comparing(TickerPriceDto::getPrice).reversed())
                    .collect(Collectors.toList());
        }
        return  marketData.getTickers()
                .get(product)
                .get(side.name())
                .stream().toList();
    }


    public List<String> getKeysOfExchangesHavingThisProduct(String product, Side side){
        List<String> validExchanges = new ArrayList<>();
        for(Map<String, HashMap<String, Queue<TickerPriceDto>>> exchange : marketData.getExchangeList()){
            if(!exchange.get(product).get(side.name()).isEmpty()){
               validExchanges.add(exchange.entrySet().toString());
            }
        }
        return validExchanges;

    }
    public List<List<TickerPriceDto>> getExchangesHavingThisProduct(String product, Side side){
        List<List<TickerPriceDto>> validExchanges = new ArrayList<>();
        for(Map<String, HashMap<String, Queue<TickerPriceDto>>> exchange : marketData.getExchangeList()){
            if(!exchange.get(product).get(side.name()).isEmpty()){
               validExchanges.add(exchange
                       .get(product)
                       .get(side.name())
                       .stream().toList());
            }
        }
        return validExchanges;

    }

//    public List<TickerPriceDto> getLastTenPricesForExchangesHavingThisProduct(String exchangeKey, String product, Side side) {
//        if (side.equals(Side.BUY)) {
//        }
//        if (side.equals(Side.SELL)) {
//        }
//    }



    public void setExchangeDataList(OrderingServiceDto orderingServiceDto) {
        this.exchangeDataList = Arrays.asList(marketData.getExchangeList());
    }

}
