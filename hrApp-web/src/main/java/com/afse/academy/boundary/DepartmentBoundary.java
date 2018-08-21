package com.afse.academy.boundary;


import com.afse.academy.exceptions.InvalidInputException;
import com.afse.academy.persistence.entities.Department;
import com.afse.academy.persistence.entities.Employee;
import com.afse.academy.services.DepartmentService;
import com.afse.academy.services.EmployeeService;
import com.afse.academy.validation.DepartmentValidationService;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class DepartmentBoundary {

    @EJB
    private DepartmentValidationService validator;

    @EJB
    private DepartmentService service;

    @EJB
    private EmployeeService employeeService;

    @Inject
    private Logger logger;

    @PostConstruct
    private void postConstruct(){
        logger.info(DepartmentBoundary.class.getSimpleName() + " initiated post construct");
    }

    public Department saveDepartment(Department department) throws InvalidInputException {
        logger.info("started save department");

        department = service.saveDepartment(department);

        return department;
    }

    public Department getDepartmentById(Long id) {
        logger.info("started get department");

        Department department = service.getDepartmentById(id);
        return department;
    }

    public List<Department> getAll() {
        logger.info("started get all");

        List<Department> departments = service.getAll();
        return departments;
    }

    public void deleteDepartmentById(Long id) throws InvalidInputException {
        logger.info("started delete department");

        service.deleteDepartmentById(id);
    }

    public Department updateDepartment(Department department) throws InvalidInputException {
        logger.info("started update department");

        department = service.updateDepartment(department);
        return department;
    }

    public List<Employee> findEmployeesByDepartmentId(Long id){
        logger.info("started find employees by department");

        List<Employee> employees = employeeService.getEmployeesByDepartmentId(id);

        return employees;
    }
}
