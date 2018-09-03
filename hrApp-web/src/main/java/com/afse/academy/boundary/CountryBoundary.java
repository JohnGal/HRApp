package com.afse.academy.boundary;

import com.afse.academy.services.CountryService;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CountryBoundary {
    @EJB
    private CountryService countryService;

    @Inject
    private Logger logger;

    @PostConstruct
    private void postConstruct() {
        logger.info(CountryBoundary.class.getSimpleName() + "initiated post construct");
    }


    @PreDestroy
    private void preDestroy() {
        logger.info(CountryBoundary.class.getSimpleName() + " initiated preDestroy");
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<String> getAllCountries() {
        List<String> countries = countryService.getAllCountries();
        return countries;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<String> getCities(String country) {
        List<String> cities = countryService.getCities(country);
        return cities;
    }
}
