package com.example;

import com.google.gson.GsonBuilder;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Path("/")
public class Main {
    @Inject
    private RessourcesHumaines RH;
    Logger logger;
    {
        try {
            logger = configLogger(Logger.getLogger("ressourcesHumaines"), "ressourcesHumaines.log");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        return "hello";
    }

    @GET
    @Path("/getEnseignants")
    public String getEnseignants()
    {
        logger.info("getEnseignants: ");
        String result = "[\n";
        List<Enseignant> resList = RH.getEnseignants();
        for (int i = 0; i < resList.size(); i++)
        {
            result += resList.get(i).toFullJSON() + ",\n";
        }
        return result.substring(0, result.length()-2) + "\n]";
    }

    @GET
    @Path("/getEnseignant")
    public String getEnseignant(@QueryParam("id") Long id)
    {
        logger.info("getEnseignant: "+id);
        if (id != null && id <= RH.countEnseignant())
        {
            Enseignant enseignant = RH.getEnseignant(id);
            return enseignant.toFullJSON();
        }
        else
        {
            return getEnseignants();
        }
    }

    private Logger configLogger(Logger logger, String filename) throws IOException {
        FileHandler fh = new FileHandler(filename);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        logger.addHandler(fh);
        return logger;
    }
}