package com.example;

import com.google.gson.Gson;

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
    private RessourcesHumaines RH;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        return "hello";
    }

    @GET
    @Path("/getEnseignants")
    public String getEnseignants()
    {
        List<Enseignant> enseignants = RH.getEnseignants();
        String result = new Gson().toJson(enseignants);
        return result;
    }

    @GET
    @Path("/getEnseignant")
    public String getEnseignant(@QueryParam("id") Long id)
    {
        if (id != null)
        {
            Enseignant enseignant = RH.getEnseignant(id);
            String result = new Gson().toJson(enseignant);
            return result;
        }
        else
        {
            return getEnseignants();
        }
    }
}