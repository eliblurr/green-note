package org.tlc.microservices.marketdataservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Component
public class RedisMessagePublish {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    public RedisMessagePublish(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void publish(String message, ChannelTopic topic) {
        System.out.println("message sent:"+ message);
        redisTemplate.convertAndSend(topic.getTopic(),message);
    }
}
