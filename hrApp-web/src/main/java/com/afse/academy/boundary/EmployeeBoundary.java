package com.afse.academy.boundary;


import com.afse.academy.exceptions.InvalidInputException;
import com.afse.academy.persistence.entities.Employee;
import com.afse.academy.services.EmployeeService;
import com.afse.academy.validation.EmployeeValidationService;
import javassist.NotFoundException;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Stateless
public class EmployeeBoundary {

    @Inject
    private Logger logger;

    @EJB
    private EmployeeValidationService validator;

    @EJB
    private EmployeeService service;

    @PostConstruct
    private void postConstruct() {
        logger.info(EmployeeBoundary.class.getSimpleName() + " initiated post construct");
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Employee createEmployee(Employee employee) throws ConstraintViolationException, InvalidInputException {
        logger.info("called create employee");

        validator.validateForCreate(employee);

        employee = service.createEmployee(employee);

        return employee;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Employee> getAll() {
        logger.info("called get all");

        List<Employee> employees = service.getAllEmployees();
        return employees;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Employee getEmployeeByID(Long id) {
        logger.info("called get employee");

        Employee employee = service.getEmployeeByID(id);

        return employee;
    }

    public void deleteEmployeeByID(Long id) throws InvalidInputException {
        logger.info("called delete employee");
        service.deleteEmployeeByID(id);
    }

    public Employee updateEmployee(Employee employee) throws InvalidInputException, NotFoundException {
        logger.info("called update employee");

        validator.validateForUpdate(employee);

        employee = service.updateEmployee(employee);
        return employee;
    }


}
