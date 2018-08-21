package com.afse.academy.validation;

import com.afse.academy.dao.EmployeeDao;
import com.afse.academy.exceptions.InvalidInputException;
import com.afse.academy.persistence.entities.Employee;
import javassist.NotFoundException;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EmployeeValidationService extends ValidationService<Employee> {

    @EJB
    private DepartmentValidationService departmentValidationService;

    @EJB
    private EmployeeDao employeeDao;

    @EJB
    private LocationValidationService locationValidationService;

    @Inject
    private Logger logger;

    @PostConstruct
    private void postConstruct() {
        logger.info(EmployeeValidationService.class.getSimpleName() + " initiated post construct");
    }

    @Override
    public void validateForCreate(Employee entity) throws InvalidInputException {
        logger.info("called validate for create");


        validate(entity);
        if (entity.getId() != null) {
            throw new InvalidInputException("Employee id is prohibited upon creation");
        }

        locationValidationService.validateLocation(entity.getAddress().getCountry(), entity.getAddress().getCity());

        departmentValidationService.checkDepartment(entity.getDepartment().getId());
    }

    @Override
    public void validateForUpdate(Employee entity) throws InvalidInputException, NotFoundException {
        logger.info("called validate update");

        validate(entity);

        if (entity.getId() == null) {
            throw new NotFoundException("Employee does not exist");
        }

        locationValidationService.validateLocation(entity.getAddress().getCountry(), entity.getAddress().getCity());

        departmentValidationService.checkDepartment(entity.getDepartment().getId());
    }

    @Override
    public void validateForDelete(Long id) throws NotFoundException {
        logger.info("called validate for delete");

        if(!employeeDao.containsByID(id)){
            throw new NotFoundException("Employee does not exist");
        }
    }
}
