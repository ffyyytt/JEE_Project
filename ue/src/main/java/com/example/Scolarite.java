package com.example;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class Scolarite extends Responsable {
    @PersistenceUnit
    EntityManager entityManager;

    @Transactional
    public List getUEs()
    {
        List result = entityManager.createQuery("select u from UE u").getResultList();
        return result;
    }

    @Transactional
    public UE getUE(Long id)
    {
        UE ue = (UE) entityManager.createQuery("select u from UE u where u.id = :id")
                .setParameter("id", id).getSingleResult();
        return ue;
    }
}
