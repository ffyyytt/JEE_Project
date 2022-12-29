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
    public List getAll()
    {
        List result = entityManager.createQuery("select u, ue, e from UE u join UE_Enseignant ue on ue.UE_ID = u.id join Enseignant e on e.id = ue.ENSEIGNANT_ID").getResultList();
        return result;
    }
}
