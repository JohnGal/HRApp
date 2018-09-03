package com.afse.academy.dao;


import com.afse.academy.persistence.entities.Email;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EmailDaoImpl implements EmailDao {

    @PersistenceContext(unitName = "hrAppJPA")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void createEmail(Email email) {
        em.persist(email);
        em.flush();
    }
}