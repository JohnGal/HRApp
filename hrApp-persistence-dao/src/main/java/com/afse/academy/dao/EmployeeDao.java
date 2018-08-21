package com.afse.academy.dao;

import com.afse.academy.persistence.entities.Employee;

import java.util.List;

public interface EmployeeDao {
    Employee save(Employee employee);

    List<Employee> findAll();

    Employee findEmployeeByID(Long id);

    void deleteEmployeeByID(Long id);

    Employee update(Employee employee);

    List<Employee> findByDepartmentID(Long id);

    boolean containsByID(Long id);

}