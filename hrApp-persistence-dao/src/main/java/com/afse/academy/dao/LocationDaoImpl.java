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

    @PersistenceContext(unitName = "hibernate-test")
    private EntityManager em;

    @Override
    public List<String> getAllCountries() {
        Query query = em.createQuery("select distinct l.country from " + Location.class.getSimpleName() + " as l order by l.country");
        List<String> countries = query.getResultList();
        return countries;
    }

    @Override
    public List<String> getCities(String country) {
        Query query = em.createQuery("select l.city from " + Location.class.getSimpleName() + " as l where l.country = ?1 order by l.city").setParameter(1, country);
        List<String> cities = query.getResultList();
        return cities;
    }

    @Override
    public boolean contains(String country, String city) {
        Query query = em.createQuery("select count(l.id) from " + Location.class.getSimpleName() + " as l where l.country = :country and l.city = :city")
                .setParameter("country", country)
                .setParameter("city", city);

//        try {
//            Object location = query.getSingleResult();
//        } catch (NoResultException e) {
//            return false;
//        }
//        return true;
        return (long)query.getSingleResult() != 0;
    }


}
