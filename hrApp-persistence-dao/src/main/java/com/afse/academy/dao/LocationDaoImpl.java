package com.afse.academy.dao;

import com.afse.academy.persistence.entities.Location;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class LocationDaoImpl implements LocationDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<String> getAllCountries() {
        Query query = em.createNamedQuery("Location.findAllCountries");
        List<String> countries = query.getResultList();
        return countries;
    }

    @Override
    public List<String> getCities(String country) {
        Query query = em.createNamedQuery("Location.findCitiesByCountry").setParameter("country", country);
        List<String> cities = query.getResultList();
        return cities;
    }

    @Override
    public boolean contains(String country, String city) {
        Query query = em.createQuery("select count(l.id) from " + Location.class.getSimpleName() + " as l where l.country = :country and l.city = :city")
                .setParameter("country", country)
                .setParameter("city", city);

        return (long) query.getSingleResult() != 0;
    }
}
