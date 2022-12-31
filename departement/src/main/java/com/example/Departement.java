package com.example;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class Departement extends Responsable {
    @PersistenceUnit
    EntityManager entityManager;

    @Transactional
    public List getEUofEnseignant(Long Enseignant_id) throws Exception {
        if (Enseignant_id >= countEnseignant())
            throw new Exception("No data");
        List result = entityManager.createQuery("select u.id, u.nom, u.semestre, u.groupesCM, u.groupesTD, u.groupesTP, ue.heuresCM, ue.heuresTD, ue.heuresTP from UE u join UE_Enseignant ue on ue.UE_ID = u.id join Enseignant e on e.id = ue.ENSEIGNANT_ID where e.id = :Enseignant_id")
                .setParameter("Enseignant_id", Enseignant_id).getResultList();
        return result;
    }

    @Transactional
    public List getEnseignantofEU(Long EU_id) throws Exception {
        if (EU_id >= countUE())
            throw new Exception("No data");
        List result = entityManager.createQuery("select e.id, e.prenom, e.nom, e.departement, e.equivalence, ue.heuresCM, ue.heuresTD, ue.heuresTP from UE u join UE_Enseignant ue on ue.UE_ID = u.id join Enseignant e on e.id = ue.ENSEIGNANT_ID where u.id = :EU_id")
                .setParameter("EU_id", EU_id).getResultList();
        return result;
    }
}
