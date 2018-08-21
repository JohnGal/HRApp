package com.afse.academy.queue;


import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;

public abstract class AbstractQueueService<T extends Serializable> {
    @Inject
    private Logger logger;

    /**
     * Lookup to initialize the {@link ConnectionFactory} and the {@link Queue}
     */
    public abstract void init();

    protected abstract ConnectionFactory getConnectionFactory();

    protected abstract Queue getQueue();

    @PostConstruct
    public void initialization() {
        init();
    }

    /**
     * @param message the actual message to be send to the {@link Queue}
     * @return true if the email was sent successfully
     */
    public boolean send(T message, int priority) {
        Connection connection = null;
        Session session = null;

        if (!isValid()) {
            return false;
        }

        try {
            connection = getConnectionFactory().createConnection();
            session = connection.createSession(true, Session.SESSION_TRANSACTED);
            ObjectMessage msg = session.createObjectMessage();
            msg.setObject(message);
            msg.setJMSPriority(priority);
            session.createProducer(getQueue()).send(msg);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            releaseConnection(connection, session);
        }

        return true;
    }

    private boolean isValid() {
        if (getConnectionFactory() == null) {
            logger.error("No connection factory was initialized to send message");
            return false;
        }

        if (getQueue() == null) {
            logger.error("No queue was initialized to send message");
            return false;
        }
        return true;
    }

    private void releaseConnection(Connection connection, Session session) {
        try {
            if (session != null) {
                session.close();
            }
        } catch (JMSException e) {
            logger.error(e.getMessage());
        }

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (JMSException e) {
            logger.error(e.getMessage());
        }
    }

    protected <T> T doLookup(String lookupName) {
        try {
            return InitialContext.doLookup(lookupName);
        } catch (NamingException e) {
            logger.error("Cannot find queue name <" + lookupName + ">", e);
            return null;
        }
    }

}
