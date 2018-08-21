package com.afse.academy.services;

import com.afse.academy.dao.DepartmentDao;
import com.afse.academy.exceptions.InvalidInputException;
import com.afse.academy.persistence.entities.Department;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class DepartmentServiceImpl implements DepartmentService {
    @EJB
    private DepartmentDao departmentDao;

    @Inject
    private Logger logger;

    @PostConstruct
    private void postConstruct(){
        logger.info(DepartmentServiceImpl.class.getSimpleName() + " initiated post construct");
    }

    @Override
    public Department getDepartmentById(Long id) {
        logger.info("started get department");

        Department department = departmentDao.findByPrimaryKey(id);
        return department;
    }

    @Override
    public List<Department> getAll() {
        logger.info("started get all departments");

        List<Department> departments = departmentDao.findAll();
        return departments;
    }

    @Override
    public Department saveDepartment(Department department) {
        logger.info("started create department");

        department = departmentDao.saveDepartment(department);
        return department;
    }

    @Override
    public Department updateDepartment(Department department) throws InvalidInputException {
        logger.info("started update department");

        if (department.getId() == null || departmentDao.findByPrimaryKey(department.getId()) == null) {
            throw new InvalidInputException("This department does not exist!");
        }
        department = departmentDao.update(department);
        return department;
    }

    @Override
    public void deleteDepartmentById(Long id) throws InvalidInputException {
        logger.info("started delete department");

        if (id == null || departmentDao.findByPrimaryKey(id) == null) {
            throw new InvalidInputException("This department does not exist");
        }

        departmentDao.deleteByPrimaryKey(id);
    }
}
