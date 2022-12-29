package com.example;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class RessourcesHumaines {
    @PersistenceUnit
    EntityManager entityManager;

    @Transactional
    public List<Enseignant> getEnseignants()
    {
        return entityManager.createQuery("select object(e) from Enseignant e").getResultList();
    }

    @Transactional
    public Enseignant getEnseignant(Long id)
    {
        return (Enseignant) entityManager.createQuery("select object(e) from Enseignant e where e.id = :id")
                .setParameter("id", id).getSingleResult();
    }

    @Transactional
    public void updateHeures(Long id, Long heures)
    {
        entityManager.createNativeQuery("update Enseignant e set e.heures = :heures where e.id = :id")
                .setParameter("id", id)
                .setParameter("heures", heures)
                .executeUpdate();
    }

    @Transactional
    public void updateEquivalence(Long id, float equivalence)
    {
        entityManager.createNativeQuery("update Enseignant e set e.equivalence = :equivalence where e.id = :id")
                .setParameter("id", id)
                .setParameter("equivalence", equivalence)
                .executeUpdate();
    }

    @Transactional
    public void addEnseignant(Enseignant enseignant)
    {
//        entityManager.createNativeQuery("insert into Enseignant (nom, prenom, heures, equivalence, departement, service) values (:nom, :prenom, :heures, :equivalence, :departement, :service)")
//                .setParameter("nom", enseignant.nom)
//                .setParameter("prenom", enseignant.prenom)
//                .setParameter("heures", enseignant.heures)
//                .setParameter("equivalence", enseignant.equivalence)
//                .setParameter("departement", enseignant.departement)
//                .setParameter("service", enseignant.service)
//                .executeUpdate();
        entityManager.persist(enseignant);
    }
}
