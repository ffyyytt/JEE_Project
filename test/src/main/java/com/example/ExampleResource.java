package com.example;

import com.google.gson.Gson;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class ExampleResource {
    @Inject
    private Scolarite scolarite;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        scolarite.init();
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("/getUEs")
    public String getEnseignants()
    {
        List ues = scolarite.getUes();
        String result = new Gson().toJson(ues);
        return result;
    }
}