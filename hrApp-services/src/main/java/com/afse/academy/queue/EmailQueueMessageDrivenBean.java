package com.afse.academy.queue;

import com.afse.academy.dao.EmailDao;
import com.afse.academy.persistence.entities.Email;
import org.apache.log4j.Logger;

import javax.ejb.*;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(name = "emailQueue", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = EmailQueueServiceImpl.EMAIL_QUEUE_NAME),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class EmailQueueMessageDrivenBean implements MessageListener {
    @Inject
    private Logger logger;

    @EJB
    private EmailDao emailDao;

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                logger.info("EMAIL BEAN: Email received " +
                        message.getBody(Email.class).getMessage());
                emailDao.createEmail(message.getBody(Email.class));
            } else {
                logger.warn("Message of wrong type:" +
                        message.getClass().getName());
            }
        } catch (JMSException e) {
            logger.error("EmailQueueMessageDrivenBean.onMessage: JMSException: ",
                    e);
        }
    }
}
