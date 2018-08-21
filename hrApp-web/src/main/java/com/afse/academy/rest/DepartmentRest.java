package com.afse.academy.rest;


import com.afse.academy.boundary.DepartmentBoundary;
import com.afse.academy.exceptions.InvalidInputException;
import com.afse.academy.persistence.entities.Department;
import com.afse.academy.persistence.entities.Employee;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/department")
public class DepartmentRest {
    @Inject
    private Logger logger;

    @EJB
    private DepartmentBoundary boundary;

    @GET
    @Path("/{id : \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartment(@PathParam("id") Long id) {
        logger.info("called get department");
        Department department = boundary.getDepartmentById(id);
        return Response.ok(department).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDepartments() {
        logger.info("called get all departments");

        List<Department> departments = boundary.getAll();
        return Response.ok(departments).build();
    }

    @GET
    @Path("/{id : \\d+}/employees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartmentEmployees(@PathParam("id") Long id) {
        logger.info("called get department employees");

        List<Employee> employees = boundary.findEmployeesByDepartmentId(id);
        return Response.ok(employees).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDepartment(Department department) throws InvalidInputException {
        logger.info("called create department");

        department = boundary.saveDepartment(department);
        return Response.ok(department).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Department department) throws InvalidInputException {
        logger.info("called get update departments");

        department = boundary.updateDepartment(department);
        return Response.ok(department).build();
    }

    @DELETE
    @Path("/{id : \\d+}")
    public Response deleteDepartment(@PathParam("id") Long id) throws InvalidInputException {
        logger.info("called get all departments");

        boundary.deleteDepartmentById(id);
        return Response.ok().build();
    }
}
