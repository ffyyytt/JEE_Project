package com.example;


import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class Responsable {
    @PersistenceUnit
    EntityManager entityManager;

    @Transactional
    public Long countUE()
    {
        return (Long) entityManager.createQuery("SELECT COUNT(DISTINCT u.id) from UE u").getSingleResult();
    }

    @Transactional
    public Long countEnseignant()
    {
        return (Long) entityManager.createQuery("SELECT COUNT(DISTINCT e.id) from Enseignant e").getSingleResult();
    }

    @Transactional
    public Long countUE_Enseignant()
    {
        return (Long) entityManager.createQuery("SELECT COUNT(DISTINCT ue.id) from UE_Enseignant ue").getSingleResult();
    }

    @Transactional
    public void addUE(UE ue)
    {
        entityManager.persist(ue);
    }

    @Transactional
    public void addEnseignant(Enseignant enseignant)
    {
        entityManager.persist(enseignant);
    }
}