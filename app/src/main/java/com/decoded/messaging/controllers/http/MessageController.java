package com.decoded.messaging.controllers.http;


import com.decoded.messaging.data.redis.MessageRedisDto;
import com.decoded.messaging.services.SmsMessagingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * MessageController
 */
@RestController
@Tag(name = "Messaging")
@RequestMapping(value = "api/v1/messages")
public class MessageController {

    @Autowired
    private SmsMessagingService messageService;

    @PostMapping(value = "log/message")
    @Operation(summary = "Send Message", description = "Send Message")
    public ResponseEntity<MessageRedisDto> sendMessage(@RequestBody MessageRedisDto messageRequest) {
        MessageRedisDto message = messageService.logMessage(messageRequest);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
