package com.example;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class Scolarite extends Responsable {
    @PersistenceUnit
    EntityManager entityManager;

    @Transactional
    public List<UE> getUEs()
    {
        List<UE> result = entityManager.createQuery("select  object(u) from UE u").getResultList();
        return result;
    }

    @Transactional
    public UE getUE(Long id) throws Exception {
        if (id >= countUE())
            throw new Exception("No data");
        UE result = (UE) entityManager.createQuery("select object(u) from UE u where u.id = :id")
                .setParameter("id", id).getSingleResult();
        return result;
    }
}
