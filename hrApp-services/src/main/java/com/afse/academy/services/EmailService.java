package com.afse.academy.services;

import com.afse.academy.persistence.entities.Email;
import com.afse.academy.persistence.entities.Employee;
import com.afse.academy.queue.EmailQueueService;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateless
public class EmailService {
    @EJB
    private EmailQueueService emailQueueService;

    @Inject
    private Logger logger;

    @PostConstruct
    private void postConstruct(){
        logger.info(EmailService.class.getSimpleName() + " initiated post construct");
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void sendRegistrationEmail(Employee employee) {
        logger.info("started send registration email");

        Email email = new Email();
        String message = "New employee \n"
                + "First name: " + employee.getFirstName()
                + "\nLast name : " + employee.getLastName();
        email.setMessage(message);
        emailQueueService.send(email);
    }
}
