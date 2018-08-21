package com.afse.academy.validation;

import com.afse.academy.dao.LocationDao;
import com.afse.academy.exceptions.InvalidInputException;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LocationValidationService {
    @EJB
    private LocationDao locationDao;

    @Inject
    private Logger logger;

    @PostConstruct
    private void postConstruct(){
        logger.info(LocationValidationService.class.getSimpleName() + " initiated post construct");
    }

    public void validateLocation(String country, String city) throws InvalidInputException {
        logger.info("started validate location");

        if(!locationDao.contains(country, city)){
            throw new InvalidInputException("This city or country does not exist");
        }
    }
}
