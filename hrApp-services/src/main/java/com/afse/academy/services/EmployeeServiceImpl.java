package com.afse.academy.services;


import com.afse.academy.dao.EmployeeDao;
import com.afse.academy.exceptions.InvalidInputException;
import com.afse.academy.persistence.entities.Employee;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class EmployeeServiceImpl implements EmployeeService {
    @EJB
    private EmployeeDao employeeDao;

    @EJB
    private EmailService emailService;

    @Inject
    private Logger logger;

    @PostConstruct
    private void postConstruct() {
        logger.info(EmployeeServiceImpl.class.getSimpleName() + " initiated post construct");
    }

    @Override
    public Employee createEmployee(Employee employee) {
        logger.info("started create employee");

        employee = employeeDao.save(employee);

        try {
            emailService.sendRegistrationEmail(employee);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        logger.info("started get all employees");

        return employeeDao.findAll();
    }

    @Override
    public Employee getEmployeeByID(Long id) {
        logger.info("started get employee");

        return employeeDao.findEmployeeByID(id);
    }

    @Override
    public Employee updateEmployee(Employee employee) throws InvalidInputException {
        logger.info("started update employee");

        if (employee.getId() == null || employeeDao.findEmployeeByID(employee.getId()) == null) {
            throw new InvalidInputException("This employee does not exist!");
        }
        employee = employeeDao.update(employee);
        return employee;
    }

    @Override
    public List<Employee> getEmployeesByDepartmentId(Long id) {
        logger.info("started get employees by department");

        List<Employee> employees = employeeDao.findByDepartmentID(id);
        return employees;
    }

    @Override
    public void deleteEmployeeByID(Long id) throws InvalidInputException {
        logger.info("started delete employee");

        if (id == null || employeeDao.findEmployeeByID(id) == null) {
            throw new InvalidInputException("This employee does not exist!");
        }

        employeeDao.deleteEmployeeByID(id);
    }
}

