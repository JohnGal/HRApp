package com.afse.academy.dao;

import com.afse.academy.persistence.entities.Department;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;
import java.util.List;

@Stateless
public class DepartmentDaoImpl implements DepartmentDao {
    @PersistenceContext(unitName = "hibernate-test")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public Department findByPrimaryKey(Long id) {
        if (id == null) {
            throw new NotFoundException("This department does not exist");
        }

        Department department = em.find(Department.class, id);

        return department;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<Department> findAll() {
        Query query = em.createQuery("select d from " + Department.class.getSimpleName() + " d order by d.name asc");
        List<Department> departments = query.getResultList();
        return departments;
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        Department department = em.find(Department.class, id);

        if (department == null) {
            throw new NotFoundException("This department does not exist");
        }

        em.remove(department);
    }

    @Override
    public Department saveDepartment(Department department) {
        em.persist(department);
        em.flush();

        return department;
    }

    @Override
    public Department update(Department department) {
        if (department.getId() == null) {
            throw new NotFoundException("This department does not exist");
        }

        Department persistedDepartment = findByPrimaryKey(department.getId());

        if (persistedDepartment == null) {
            throw new NotFoundException("This department does not exist");
        }

        persistedDepartment.updateValues(department);
        return persistedDepartment;
    }

    @Override
    public Boolean containsByID(Long id) {

        Query query = em.createQuery("select count(d.id) from " + Department.class.getSimpleName() + " as d where d.id = :id")
                .setParameter("id", id);

        return (long) query.getSingleResult() != 0;
    }


}
