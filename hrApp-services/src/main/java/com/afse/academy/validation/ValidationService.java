package com.afse.academy.validation;

import com.afse.academy.exceptions.InvalidInputException;
import javassist.NotFoundException;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import java.io.Serializable;

public abstract class ValidationService<T extends Serializable> {

    @EJB
    private EntityValidationService<T> entityValidationService;

    @Inject
    private Logger logger;

    @PostConstruct
    private void postConstruct() {
        logger.info(ValidationService.class.getSimpleName() + " initiated post construct");
    }

    public void validate(T entity) {
        logger.info("started validate");

        entityValidationService.validate(entity);
    }

    public abstract void validateForCreate(T entity) throws InvalidInputException;

    public abstract void validateForUpdate(T entity) throws InvalidInputException, NotFoundException;

    public abstract void validateForDelete(Long id) throws InvalidInputException, NotFoundException;

}
