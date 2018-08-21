package com.afse.academy.validation;

import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.io.Serializable;
import java.util.Set;

@Stateless
public class EntityValidationService <T extends Serializable> {

    @Inject
    private Validator validator;

    @Inject
    private Logger logger;

    @PostConstruct
    private void postConstruct(){
        logger.info(EntityValidationService.class.getSimpleName() + " initiated post construct");
    }

    public void validate(T entity) {
        logger.info("started validate");

        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);

        if(!constraintViolations.isEmpty()){
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
