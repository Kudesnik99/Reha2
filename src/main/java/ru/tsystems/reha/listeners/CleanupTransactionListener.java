package ru.tsystems.reha.listeners;

import org.apache.log4j.Logger;
import org.springframework.jms.JmsException;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import ru.tsystems.reha.jms.JmsProducer;
import ru.tsystems.reha.jms.messages.UpdateEventsListMsg;

public class CleanupTransactionListener extends TransactionSynchronizationAdapter {
    private final JmsProducer jmsProducer;
    private final UpdateEventsListMsg msg;
    //private final String UPDATE_MSG = "Update_event";

    private static final Logger LOG = Logger.getLogger(CleanupTransactionListener.class);

    public CleanupTransactionListener(JmsProducer jmsProducer, UpdateEventsListMsg msg) {
        this.jmsProducer = jmsProducer;
        this.msg = msg;
    }

    @Override
    public void afterCompletion(int status) {
        if (STATUS_COMMITTED == status) {
            try {
                jmsProducer.send(msg.toString());
            } catch (JmsException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }
}