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
    private Scolarite scolarite;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        scolarite.init();
        return "hello";
    }

    @GET
    @Path("/getUEs")
    public String getEnseignants()
    {
        List ues = scolarite.getUEs();
        String result = new GsonBuilder().setPrettyPrinting().create().toJson(ues);
        return result;
    }
}