package com.decoded.messaging.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Configuration
@ConfigurationProperties(prefix = "decoded")
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@ToString
public class ApplicationConfig {
    
    private AfricasTalkingConfig africastalking;

    @Getter(value = AccessLevel.PUBLIC)
    @Setter(value = AccessLevel.PUBLIC)
    @ToString
    public static class AfricasTalkingConfig {
        private String username;

        private String apiKey;

        private String defaultFrom;
    }
}
