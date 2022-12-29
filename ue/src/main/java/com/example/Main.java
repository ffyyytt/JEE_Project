package com.example;

import com.google.gson.GsonBuilder;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class Main {
    @Inject
    private Scolarite scolarite;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        return "hello";
    }

    @GET
    @Path("/getUEs")
    public String getUEs()
    {
        List ues = scolarite.getUEs();
        String result = new GsonBuilder().setPrettyPrinting().create().toJson(ues);
        return result;
    }

    @GET
    @Path("/getUE")
    public String getUE(@QueryParam("id") Long id)
    {
        if (id != null)
        {
            UE ue = scolarite.getUE(id);
            String result = new GsonBuilder().setPrettyPrinting().create().toJson(ue);
            return result;
        }
        else
        {
            return getUEs();
        }
    }
}