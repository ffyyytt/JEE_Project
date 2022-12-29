package com.example;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class RessourcesHumaines extends Responsable {
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
}
