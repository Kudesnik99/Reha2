package ru.tsystems.reha.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JmsProducer {
    private final JmsTemplate jmsTemplate;

    @Value("${jms.queue.destination}")
    String destinationQueue;

    @Autowired
    public JmsProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


    public void send(String msg) {
        jmsTemplate.convertAndSend(destinationQueue, msg, message -> {
            message.setJMSMessageID(UUID.randomUUID().toString());
            return message;
        });
    }
}