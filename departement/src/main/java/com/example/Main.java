package com.example;

import com.google.gson.GsonBuilder;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class Main {
    @Inject
    private Departement departement;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        departement.init();
        return "Hello";
    }

    @GET
    @Path("/getall")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAll() {
        List ues = departement.getAll();
        String result = new GsonBuilder().setPrettyPrinting().create().toJson(ues);
        return result;
    }
}