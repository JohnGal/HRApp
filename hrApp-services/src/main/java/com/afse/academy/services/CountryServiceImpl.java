package com.afse.academy.services;

import com.afse.academy.dao.LocationDao;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CountryServiceImpl implements CountryService {

    @EJB
    private LocationDao locationDao;

    @Inject
    private Logger logger;

    @PostConstruct
    private void postConstruct(){
        logger.info(CountryServiceImpl.class.getSimpleName() + " initiated post construct");
    }

    @Override
    public List<String> getAllCountries() {
        logger.info("started get all countries");

        return locationDao.getAllCountries();
    }

    @Override
    public List<String> getCities(String country) {
        logger.info("started get cities");

        return locationDao.getCities(country);
    }
}
