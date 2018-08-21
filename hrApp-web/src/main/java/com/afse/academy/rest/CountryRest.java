package com.afse.academy.rest;

import com.afse.academy.boundary.CountryBoundary;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/country")
@Produces(MediaType.APPLICATION_JSON)
public class CountryRest {

    @Inject
    private Logger logger;

    @EJB
    private CountryBoundary boundary;

    @GET
    public Response getAllCountries() {
        logger.info("called get all countries");


        List<String> countries = boundary.getAllCountries();
//        return Response.status(200).entity(result).build();
        return Response.ok(countries).build();
    }

    @GET
    @Path("/{country}/cities")
    public Response getCities(@PathParam("country") String country) {
        logger.info("called get all cities");

        List<String> cities = boundary.getCities(country);
        return Response.ok(cities).build();
    }
}