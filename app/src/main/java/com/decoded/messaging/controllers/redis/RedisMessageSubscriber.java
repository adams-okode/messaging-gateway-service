package com.decoded.messaging.controllers.redis;

import com.decoded.messaging.data.redis.MessageRedisDto;
import com.decoded.messaging.services.SmsMessagingService;
import com.fasterxml.jackson.core.JsonProcessingException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisMessageSubscriber {

    @Autowired
    private RedisTemplate<?, ?> redisTemplate;

    @Autowired
    private SmsMessagingService smsMessagingService;

    

    public void onMessage(String message) throws JsonProcessingException {
        MessageRedisDto ms = (MessageRedisDto) this.redisTemplate.getValueSerializer().deserialize(message.getBytes());
        System.out.println(ms);
        smsMessagingService.sendMessages(ms);
    }

}

