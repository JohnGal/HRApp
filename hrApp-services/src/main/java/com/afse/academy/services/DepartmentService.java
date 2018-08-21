package com.afse.academy.services;

import com.afse.academy.exceptions.InvalidInputException;
import com.afse.academy.persistence.entities.Department;

import java.util.List;

public interface DepartmentService {
    Department getDepartmentById(Long id);

    List<Department> getAll();

    Department saveDepartment(Department department);

    Department updateDepartment(Department department) throws InvalidInputException;

    void deleteDepartmentById(Long id) throws InvalidInputException;
}
