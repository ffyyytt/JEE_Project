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
    private Departement departement;
    Logger logger;
    {
        try {
            logger = configLogger(Logger.getLogger("departement"), "departement.log");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        departement.init();
        return "Hello";
    }

    @GET
    @Path("/getEUofEnseignant")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEUofEnseignant(@QueryParam("id") Long id) {
        logger.info("getEUofEnseignant: " + id);
        if (id != null && id <= departement.countEnseignant())
        {
            String[] columnName = {"", "", "\"id\": ", "\"nom\": ", "\"semestre\": ", "\"groupesCM\": ", "\"groupesTD\": ", "\"groupesTP\": ", "\"heuresCM\": ", "\"heuresTD\": ",  "\"heuresTP\": "};
            List resList = departement.getEUofEnseignant(id);
            String result = new GsonBuilder().setPrettyPrinting().create().toJson(resList);
            return formatJSON(columnName, result);
        }
        return "{}";
    }

    @GET
    @Path("/getEnseignantofEU")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEnseignantofEU(@QueryParam("id") Long id) {
        logger.info("getEnseignantofEU: " + id);
        if (id != null && id <= departement.countUE())
        {
            String[] columnName = {"", "", "\"id\": ", "\"prenom\": ", "\"nom\": ", "\"departement\": ", "\"equivalence\": ", "\"heuresCM\": ", "\"heuresTD\": ", "\"heuresTP\": "};
            List resList = departement.getEnseignantofEU(id);
            String result = new GsonBuilder().setPrettyPrinting().create().toJson(resList);
            return formatJSON(columnName, result);
        }
        return "{}";
    }

    private int getPre(String s)
    {
        int result;
        for (result = 0; result < s.length() && s.charAt(result) == ' '; result++);
        return result;
    }

    private String formatJSON(String[] columnName, String data)
    {
        String[] array = data.split("\n", -1);
        for (int i = 0; i < array.length; i++)
        {
            int index = getPre(array[i]);
            array[i] = array[i].substring(0, index) + columnName[i%columnName.length] + array[i].substring(index);
        }
        return String.join("\n", array);
    }

    private Logger configLogger(Logger logger, String filename) throws IOException {
        FileHandler fh = new FileHandler(filename);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        logger.addHandler(fh);
        return logger;
    }
}