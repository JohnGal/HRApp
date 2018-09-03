package com.afse.academy.mappers;

import com.afse.academy.exceptions.InvalidInputException;

import javax.ejb.EJBException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class HRAppExceptionMapper implements ExceptionMapper<Exception> {


    @Override
    public Response toResponse(Exception e) {

        if (e instanceof InvalidInputException) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        if (e instanceof EJBException) {
            if (e.getCause() instanceof ConstraintViolationException) {

                return Response.status(Response.Status.BAD_REQUEST).entity(prepareMessage((ConstraintViolationException) (e.getCause()))).build();
            }
        }

        if (e instanceof NotFoundException) {
            return Response.status(Response.Status.NOT_FOUND).entity("The entity is not found!").build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
    }

    private String prepareMessage(ConstraintViolationException exception) {
        String msg = "";
        for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
            msg += cv.getPropertyPath() + " " + cv.getMessage() + "\n";
        }
        return msg;
    }


}
