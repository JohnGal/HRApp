package com.afse.academy.rest;


import com.afse.academy.boundary.EmployeeBoundary;
import com.afse.academy.exceptions.InvalidInputException;
import com.afse.academy.persistence.entities.Employee;
import javassist.NotFoundException;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employee")
public class EmployeeRest {
    @EJB
    private EmployeeBoundary boundary;

    @Inject
    private Logger logger;

    @PostConstruct
    private void postConstruct(){
        logger.info(EmployeeRest.class.getSimpleName() + " initiated postConstruct");
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEmployee(Employee employee) throws InvalidInputException {
        logger.info("called create employee");

        employee = boundary.createEmployee(employee);
        return Response.ok(employee).build();
    }

    @GET
    @Path("/{id : \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployee(@PathParam("id") Long id) {
        logger.info("called get employee");

        Employee employee = boundary.getEmployeeByID(id);
        return Response.ok(employee).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployee() {
        logger.info("called get all employee");

        List<Employee> employees = boundary.getAll();
        return Response.ok(employees).build();
    }

    @DELETE
    @Path("/{id : \\d+}")
    public Response deleteEmployee(@PathParam("id") Long id) throws InvalidInputException {
        logger.info("called delete employee");

        boundary.deleteEmployeeByID(id);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(Employee employee) throws InvalidInputException, NotFoundException {
        logger.info("called update employee");

        employee = boundary.updateEmployee(employee);
        return Response.ok(employee).build();
    }
}
