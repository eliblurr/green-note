package org.tlc.microservices.marketdataservice.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.tlc.microservices.marketdataservice.model.TickerPrice;
import java.util.List;

@Repository
public class TickerPriceRepository {
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String,Object> template;

    public TickerPrice saveTicker(TickerPrice tickerPrice){
        template.opsForHash().put("TickerPrice",tickerPrice.getId(),tickerPrice);
        return tickerPrice;
    }

    public TickerPrice removeOutdatedTickers(TickerPrice tickerPrice){
//        template.opsForList().rightPopAndLeftPush("TickerPrice","TickerPrice");
        template.opsForHash().put("TickerPrice",tickerPrice.getId(),tickerPrice);
        template.opsForList().rightPop("pop");
        return tickerPrice;
    }

    public List<Object> findAll(){
        System.out.println("from db");
        return template.opsForHash().values("TickerPrice");
    }
    //querry to get by echange and ticker
    public TickerPrice findByTickerName(String ticker){
        System.out.println("from db");
        return (TickerPrice) template.opsForHash().get("TicketPrice",ticker);
    }
}

