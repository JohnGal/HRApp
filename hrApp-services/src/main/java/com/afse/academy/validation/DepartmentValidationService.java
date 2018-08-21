package com.afse.academy.validation;


import com.afse.academy.dao.DepartmentDao;
import com.afse.academy.exceptions.InvalidInputException;
import com.afse.academy.persistence.entities.Department;
import javassist.NotFoundException;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class DepartmentValidationService extends ValidationService<Department> {
    @EJB
    private LocationValidationService locationValidationService;

    @EJB
    private DepartmentDao departmentDao;

    @Inject
    private Logger logger;

    @PostConstruct
    private void postConstruct() {
        logger.info(DepartmentValidationService.class.getSimpleName() + "initiated post construct");
    }

    @Override
    public void validateForCreate(Department entity) throws InvalidInputException {
        logger.info("started validate for create");

        validate(entity);

        validate(entity);
        if (entity.getId() != null) {
            throw new InvalidInputException("Department id is prohibited upon creation");
        }

        locationValidationService.validateLocation(entity.getAddress().getCountry(), entity.getAddress().getCity());
    }

    @Override
    public void validateForUpdate(Department entity) throws InvalidInputException {
        logger.info("started validate for create");

        validate(entity);

        if (entity.getId() == null) {
            throw new InvalidInputException("Department does not exist");
        }

        locationValidationService.validateLocation(entity.getAddress().getCountry(), entity.getAddress().getCity());


    }

    @Override
    public void validateForDelete(Long id) throws NotFoundException {
        logger.info("called validate for delete");

        if (!departmentDao.containsByID(id)) {
            throw new NotFoundException("Department does not exist");
        }
    }

    public void checkDepartment(Long id) throws InvalidInputException {
        logger.info("called check department");

        if (!departmentDao.containsByID(id)) {
            throw new InvalidInputException("Department does not exist");
        }
    }
}
