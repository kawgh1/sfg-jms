package com.kwgdev.sfgjms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * created by kw on 1/2/2021 @ 7:20 AM
 */
@Configuration
public class JmsConfig {

    public static final String MY_QUEUE = "my-hello-world";
    public static final String MY_SEND_RCV_QUEUE = "replybacktome";

    // Here we have a Message Converter Bean in the Spring Context
    // when we send a message to JMS, Spring is going to convert that message to a JMS text message
    // and the payload ig going to be take that Java object and converting that to a JSON payload
    // using MappingJackson2MessageConverter

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        // Set JMS Message type as Text Message
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
