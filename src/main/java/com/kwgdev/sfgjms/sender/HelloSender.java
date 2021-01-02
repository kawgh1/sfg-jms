package com.kwgdev.sfgjms.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwgdev.sfgjms.config.JmsConfig;
import com.kwgdev.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

/**
 * created by kw on 1/2/2021 @ 7:29 AM
 */
@RequiredArgsConstructor // for JmsTemplate and Object Mapper constructors
@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper; // Jackson object mapper

    @Scheduled(fixedRate = 2000)
    public void sendMessage(){

        HelloWorldMessage message = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("Hello World!")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);

    }

    @Scheduled(fixedRate = 2000)
    public void sendandReceiveMessage() throws JMSException {

        HelloWorldMessage message = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("Hello")
                .build();

        Message receviedMsg = jmsTemplate.sendAndReceive(JmsConfig.MY_SEND_RCV_QUEUE, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message helloMessage = null;

                try {
                    helloMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
                    helloMessage.setStringProperty("_type", "com.kwgdev.sfgjms.model.HelloWorldMessage");

                    System.out.println("Sending Hello");

                    return helloMessage;

                } catch (JsonProcessingException e) {
                    throw new JMSException("boom");
                }
            }
        });

        assert receviedMsg != null;
        System.out.println(receviedMsg.getBody(String.class));

    }

}