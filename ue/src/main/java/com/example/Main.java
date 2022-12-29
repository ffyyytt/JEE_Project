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
    private Scolarite scolarite;
    Logger logger;
    {
        try {
            logger = configLogger(Logger.getLogger("scolarite"), "scolarite.log");
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
    @Path("/getUEs")
    public String getUEs()
    {
        logger.info("getUEs");
        String result = "[\n";
        List<UE> resList = scolarite.getUEs();
        for (int i = 0; i < resList.size(); i++)
        {
            result += resList.get(i).toFullJSON() + ",\n";
        }
        return result.substring(0, result.length()-2) + "\n]";
    }

    @GET
    @Path("/getUE")
    public String getUE(@QueryParam("id") Long id)
    {
        logger.info("getUE: " + id);
        if (id != null)
        {
            UE ue = scolarite.getUE(id);
            return ue.toFullJSON();
        }
        else
        {
            return getUEs();
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