package com.example;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        return "hello";
    }

    @GET
    @Path("/getUEs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEs()
    {
        logger.info("getUEs");
        String result = "[\n";
        List<UE> resList = scolarite.getUEs();
        for (int i = 0; i < resList.size(); i++)
        {
            result += resList.get(i).toFullJSON() + ",\n";
        }
        result = result.substring(0, result.length()-2) + "\n]";
        return Response.ok(result, MediaType.TEXT_PLAIN).build();
    }

    @GET
    @Path("/getUE")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUE(@QueryParam("id") Long id) {
        logger.info("getUE: " + id);
        if (id != null)
        {
            UE ue = null;
            try {
                ue = scolarite.getUE(id);
            } catch (Exception e) {
                logger.info(e.getMessage());
                return new ExceptionMapper().toResponse(e);
            }
            return Response.ok(ue.toFullJSON(), MediaType.TEXT_PLAIN).build();
        }
        return Response.status(500).entity(null).type(MediaType.TEXT_PLAIN).build();
    }

    private Logger configLogger(Logger logger, String filename) throws Exception {
        FileHandler fh = new FileHandler(filename);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        logger.addHandler(fh);
        return logger;
    }
}