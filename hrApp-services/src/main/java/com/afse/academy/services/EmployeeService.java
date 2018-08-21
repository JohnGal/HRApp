package com.afse.academy.services;

import com.afse.academy.exceptions.InvalidInputException;
import com.afse.academy.persistence.entities.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeByID(Long id);

    void deleteEmployeeByID(Long id) throws InvalidInputException;

    Employee updateEmployee(Employee employee) throws InvalidInputException;

    List<Employee> getEmployeesByDepartmentId(Long id);
}