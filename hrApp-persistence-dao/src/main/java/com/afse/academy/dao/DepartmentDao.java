package com.afse.academy.dao;


import com.afse.academy.persistence.entities.Department;

import java.util.List;

public interface DepartmentDao {
    Department findByPrimaryKey(Long id);

    List<Department> findAll();

    void deleteByPrimaryKey(Long id);

    Department saveDepartment(Department department);

    Department update(Department department);

    Boolean containsByID(Long id);
}
