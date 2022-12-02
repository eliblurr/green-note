package org.tlc.microservices.marketdataservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

public class RedisMessagePublish {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private ChannelTopic topic;

    public RedisMessagePublish(RedisTemplate<String, Object> redisTemplate, ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    public RedisMessagePublish() {
    }

    public void publish(String message) {
        redisTemplate.convertAndSend(topic.getTopic(),message);
        System.out.println("message sent:"+ message);
    }
}
