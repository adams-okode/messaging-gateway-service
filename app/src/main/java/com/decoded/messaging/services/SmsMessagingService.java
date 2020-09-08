package com.decoded.messaging.services;

import java.io.IOException;
import java.util.List;

import com.africastalking.AfricasTalking;
import com.africastalking.SmsService;
import com.africastalking.sms.Recipient;
import com.decoded.messaging.controllers.redis.RedisMessagePublisher;
import com.decoded.messaging.data.entities.MessageStatus;
import com.decoded.messaging.data.entities.MessageType;
import com.decoded.messaging.data.redis.MessageRedisDto;
import com.decoded.messaging.repositories.MessageRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsMessagingService {

    @Autowired
    private MessageRepository messageRepository;

    @Value("${decoded.africastalking.username}")
    private String africasTalkingUserName;

    @Value("${decoded.africastalking.api-key}")
    private String africasTalkingApiKey;

    @Value("${decoded.africastalking.default-from}")
    private String africasTalkingDefaultFrom;

    @Autowired
    private RedisMessagePublisher redisMessagePublisher;

    /**
     * @param messageRequest
     * @return
     */
    public MessageRedisDto logMessage(MessageRedisDto messageRequest) {
        MessageRedisDto message = new MessageRedisDto();
        message.setRecepient(messageRequest.getRecepient());
        message.setContent(messageRequest.getContent());
        message.setStatus(MessageStatus.PENDING);
        message.setType(messageRequest.getType());
        message.setSubject(messageRequest.getSubject());
        redisMessagePublisher.publish(message);
        return message;
    }

    /**
     * 
     * @param messages
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */
    public void sendMessages(MessageRedisDto message) throws JsonMappingException, JsonProcessingException {
        // Initialize
        AfricasTalking.initialize(africasTalkingUserName, africasTalkingApiKey);

        SmsService sms = AfricasTalking.getService(AfricasTalking.SERVICE_SMS);
        try {
            if (message.getType() == MessageType.SMS) {
                List<Recipient> response = sms.send(message.getContent(), africasTalkingDefaultFrom,
                        new String[] { "+" + message.getRecepient() }, true);
                if (!response.isEmpty()) {
                    message.setStatus(MessageStatus.SENT);
                }
            }
            
        } catch (IOException e) {
            Integer retries = message.getRetries() + 1;
            message.setRetries(retries);
        }
        ObjectMapper mapper = new ObjectMapper();

        com.decoded.messaging.data.entities.Message ms = mapper.readValue(mapper.writeValueAsString(message),
        com.decoded.messaging.data.entities.Message.class);
        messageRepository.save(ms);

    }
}
