package com.decoded.messaging.data.redis;

import java.io.Serializable;

import com.decoded.messaging.data.entities.MessageStatus;
import com.decoded.messaging.data.entities.MessageType;

import org.springframework.data.redis.core.RedisHash;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@RedisHash("messages")
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@ToString
public class MessageRedisDto implements Serializable {
    private String id;

    private static final long serialVersionUID = 1L;

    private String recepient;

    private MessageStatus status;

    private MessageType type;

    private Integer retries;

    private String subject;

    private String content;
   
}
