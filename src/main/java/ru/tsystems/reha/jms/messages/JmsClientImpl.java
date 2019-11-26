package ru.tsystems.reha.jms.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsystems.reha.jms.JmsClient;
import ru.tsystems.reha.jms.JmsProducer;

@Service
public class JmsClientImpl implements JmsClient {

    private final
    JmsProducer jmsProducer;

    @Autowired
    public JmsClientImpl(JmsProducer jmsProducer) {
        this.jmsProducer = jmsProducer;
    }

    public void send(String msg) {
        jmsProducer.send(msg);
    }

}
