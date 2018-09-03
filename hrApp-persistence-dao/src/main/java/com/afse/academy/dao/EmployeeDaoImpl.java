package com.afse.academy.dao;


import com.afse.academy.persistence.entities.Employee;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;
import java.util.List;

@Stateless
public class EmployeeDaoImpl implements EmployeeDao {

    @PersistenceContext(unitName = "hrAppJPA")
    private EntityManager em;

    @EJB
    DepartmentDao departmentDao;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Employee save(Employee employee) {
        em.persist(employee);
        em.flush();

        return employee;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<Employee> findAll() {
        Query query = em.createQuery("select e from " + Employee.class.getSimpleName() + " e order by e.firstName, e.lastName asc");
        List<Employee> employees = query.getResultList();
        return employees;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public Employee findEmployeeByID(Long id) {

        Employee employee = em.find(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteEmployeeByID(Long id) {
        Employee employee = em.find(Employee.class, id);

        if (employee == null) {
            throw new NotFoundException("This employee does not exist");
        }
        em.remove(employee);
    }

    @Override
    public Employee update(Employee employee) {
        if (employee.getId() == null) {
            throw new NotFoundException("This employee does not exist");
        }

        Employee persistedEmployee = findEmployeeByID(employee.getId());

        if (persistedEmployee == null) {
            throw new NotFoundException("This employee does not exist");
        }

        persistedEmployee.updateValues(employee);
        return persistedEmployee;
    }

    @Override
    public List<Employee> findByDepartmentID(Long id) {
        if (!departmentDao.containsByID(id)) {
            throw new NotFoundException("This employee does not exist");
        }

        Query query = em.createQuery("select e from " + Employee.class.getSimpleName()
                + " e where e.department.id = :department_id order by e.firstName, e.lastName")
                .setParameter("department_id", id);
        List<Employee> employees = query.getResultList();
        return employees;
    }

    @Override
    public boolean containsByID(Long id) {
        Query query = em.createQuery("select count(d.id) from " + Employee.class.getSimpleName() + " as d where d.id = :id")
                .setParameter(":id", id);

        return (long) query.getSingleResult() != 0;
    }


}
