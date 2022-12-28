package com.example;

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
        RH.init();
        return "hello";
    }

    @GET
    @Path("/getEnseignants")
    public String getEnseignants()
    {
        String res = "[\n";
        List<Enseignant> enseignants = RH.getEnseignants();
        for (int i = 0; i<enseignants.size(); i++)
        {
            if (i != 0) res += ",\n";
            res += "\t{\n\t\t\"id\": " + (enseignants.get(i).id) +
                    ",\n\t\t\"None\": \"" + enseignants.get(i).nom + "\"" +
                    ",\n\t\t\"Prenom\": \"" + enseignants.get(i).prenom + "\"" +
                    ",\n\t\t\"Heures\": " + enseignants.get(i).heures +
                    ",\n\t\t\"Equivalence\": " + enseignants.get(i).equivalence +
                    ",\n\t\t\"Departement\": \"" + enseignants.get(i).departement + "\"" +
                    ",\n\t\t\"service\": " + enseignants.get(i).service + "\n\t}";
        }
        res += "\n]";
        return res;
    }

    @GET
    @Path("/getEnseignant")
    public String getEnseignant(@QueryParam("id") Long id)
    {
        if (id != null)
        {
            Enseignant enseignant = RH.getEnseignant(id);
            String res = "{\n\t\"id\": " + (enseignant.id) +
                    ",\n\t\"None\": \"" + enseignant.nom + "\"" +
                    ",\n\t\"Prenom\": \"" + enseignant.prenom + "\"" +
                    ",\n\t\"Heures\": " + enseignant.heures +
                    ",\n\t\"Equivalence\": " + enseignant.equivalence +
                    ",\n\t\"Departement\": \"" + enseignant.departement + "\"" +
                    ",\n\t\"service\": " + enseignant.service + "\n}";
            return res;
        }
        else
        {
            return getEnseignants();
        }
    }
}