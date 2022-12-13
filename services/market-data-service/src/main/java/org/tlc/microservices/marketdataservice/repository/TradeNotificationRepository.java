package org.tlc.microservices.marketdataservice.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.tlc.microservices.marketdataservice.model.TradeNotification;

import java.util.List;

@Repository
public class TradeNotificationRepository {
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String,Object> template;

    public TradeNotification saveTicker(TradeNotification order){
        template.opsForHash().put("TradeNotification",order.getTradeId(),order);
        return order;
    }

    public List<Object> findAll(){
        return template.opsForHash().values("TradeNotification");
    }

    public TradeNotification findByTradeId(String TradeId){
        return (TradeNotification) template.opsForHash().get("TicketPrice",TradeId);
    }

    public Boolean tradeExist(String TradeId){
        return findAll().contains(TradeId);
    }


}

