package org.tlc.microservices.marketdataservice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.tlc.microservices.marketdataservice.model.Exchange;

import java.util.List;

@Repository
public class ExchangeRepo {
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String,Object> template;

    public Exchange saveExchange(Exchange exchange){
        template.opsForHash().put("Exchange",exchange.getExchangeName(),exchange);
        return exchange;
    }

    public List<Object> findAllExchanges(){
        return template.opsForHash().values("Exchange");
    }

    public Exchange findByExchangeId(String exchangeId){
        return (Exchange) template.opsForHash().get("Exchange",exchangeId);
    }

}
