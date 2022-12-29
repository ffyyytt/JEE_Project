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

    @Transactional
    public void init()
    {
        entityManager.createNativeQuery("CREATE TABLE IF NOT EXISTS UE(id bigint generated by default as identity, groupesCM bigint, groupesTD bigint, groupesTP bigint, heuresCM bigint, heuresTD bigint, heuresTP bigint, nom varchar(255), semestre bigint, primary key (id));").executeUpdate();
        entityManager.createNativeQuery("CREATE TABLE IF NOT EXISTS ENSEIGNANT(id bigint generated by default as identity, departement varchar(255), equivalence float not null, heures bigint, nom varchar(255), prenom varchar(255), service varchar(255), primary key (id));").executeUpdate();
        entityManager.createNativeQuery("CREATE TABLE IF NOT EXISTS UE_ENSEIGNANT(id bigint generated by default as identity, UE_ID bigint not null, ENSEIGNANT_ID bigint not null, heuresCM bigint, heuresTD bigint, heuresTP bigint, primary key (UE_ID, ENSEIGNANT_ID));").executeUpdate();

        if (countUE() != 0 || countEnseignant() != 0 || countUE_Enseignant() != 0)
            return;

        entityManager.createNativeQuery("alter table UE_ENSEIGNANT add constraint FK_ENSEIGNANT foreign key (ENSEIGNANT_ID) references ENSEIGNANT;").executeUpdate();
        entityManager.createNativeQuery("alter table UE_ENSEIGNANT add constraint FK_UE foreign key (UE_ID) references UE;").executeUpdate();


        Enseignant enseignant1 = new Enseignant();
        enseignant1.setData("DUONG", "Anh-Kiet-1", 192L, 0.7F, "FST", "AI"); // CM:90, TD: 60, TP: 42
        addEnseignant(enseignant1);

        Enseignant enseignant2 = new Enseignant();
        enseignant2.setData("DUONG", "Anh-Kiet-2", 384L, 0.7F, "FST", "Crypto"); // CM: 180, TD: 120, TP: 84
        addEnseignant(enseignant2);

        Enseignant enseignant3 = new Enseignant();
        enseignant3.setData("DUONG", "Anh-Kiet-3", 64L, 0.7F, "FST", "Java"); // CM: 30, TD: 20, TP: 14
        addEnseignant(enseignant3);

        List<Enseignant> enseignants1 = new ArrayList<Enseignant>();
        enseignants1.add(enseignant2);
        enseignants1.add(enseignant3);

        List<Enseignant> enseignants2 = new ArrayList<Enseignant>();
        enseignants2.add(enseignant1);
        enseignants2.add(enseignant3);

        List<Enseignant> enseignants3 = new ArrayList<Enseignant>();
        enseignants3.add(enseignant1);
        enseignants3.add(enseignant2);

        UE ue1 = new UE();
        ue1.setData("M1 Informatique-1", 7L, 135L, 90L, 63L, 150L, 48L, 24L, enseignants1);
        addUE(ue1);

        UE ue2 = new UE();
        ue2.setData("M1 Informatique-2", 7L, 45L, 30L, 21L, 150L, 48L, 24L, enseignants2);
        addUE(ue2);

        UE ue3 = new UE();
        ue3.setData("M1 Informatique-3", 7L, 120L, 80L, 56L, 150L, 48L, 24L, enseignants3);
        addUE(ue3);

        entityManager.createNativeQuery("UPDATE UE_ENSEIGNANT ue set ue.heuresCM = :heuresCM, ue.heuresTD = :heuresTD, ue.heuresTP = :heuresTP where ue.UE_ID = :UE_ID and ue.ENSEIGNANT_ID = :ENSEIGNANT_ID")
                .setParameter("heuresCM", 30L)
                .setParameter("heuresTD", 20L)
                .setParameter("heuresTP", 14L)
                .setParameter("UE_ID", 2L)
                .setParameter("ENSEIGNANT_ID", 1L)
                .executeUpdate();

        entityManager.createNativeQuery("UPDATE UE_ENSEIGNANT ue set ue.heuresCM = :heuresCM, ue.heuresTD = :heuresTD, ue.heuresTP = :heuresTP where ue.UE_ID = :UE_ID and ue.ENSEIGNANT_ID = :ENSEIGNANT_ID")
                .setParameter("heuresCM", 60L)
                .setParameter("heuresTD", 40L)
                .setParameter("heuresTP", 28L)
                .setParameter("UE_ID", 3L)
                .setParameter("ENSEIGNANT_ID", 1L)
                .executeUpdate();

        entityManager.createNativeQuery("UPDATE UE_ENSEIGNANT ue set ue.heuresCM = :heuresCM, ue.heuresTD = :heuresTD, ue.heuresTP = :heuresTP where ue.UE_ID = :UE_ID and ue.ENSEIGNANT_ID = :ENSEIGNANT_ID")
                .setParameter("heuresCM", 120L)
                .setParameter("heuresTD", 80L)
                .setParameter("heuresTP", 56L)
                .setParameter("UE_ID", 1L)
                .setParameter("ENSEIGNANT_ID", 2L)
                .executeUpdate();

        entityManager.createNativeQuery("UPDATE UE_ENSEIGNANT ue set ue.heuresCM = :heuresCM, ue.heuresTD = :heuresTD, ue.heuresTP = :heuresTP where ue.UE_ID = :UE_ID and ue.ENSEIGNANT_ID = :ENSEIGNANT_ID")
                .setParameter("heuresCM", 60L)
                .setParameter("heuresTD", 40L)
                .setParameter("heuresTP", 28L)
                .setParameter("UE_ID", 3L)
                .setParameter("ENSEIGNANT_ID", 2L)
                .executeUpdate();

        entityManager.createNativeQuery("UPDATE UE_ENSEIGNANT ue set ue.heuresCM = :heuresCM, ue.heuresTD = :heuresTD, ue.heuresTP = :heuresTP where ue.UE_ID = :UE_ID and ue.ENSEIGNANT_ID = :ENSEIGNANT_ID")
                .setParameter("heuresCM", 15L)
                .setParameter("heuresTD", 10L)
                .setParameter("heuresTP", 7L)
                .setParameter("UE_ID", 1L)
                .setParameter("ENSEIGNANT_ID", 3L)
                .executeUpdate();

        entityManager.createNativeQuery("UPDATE UE_ENSEIGNANT ue set ue.heuresCM = :heuresCM, ue.heuresTD = :heuresTD, ue.heuresTP = :heuresTP where ue.UE_ID = :UE_ID and ue.ENSEIGNANT_ID = :ENSEIGNANT_ID")
                .setParameter("heuresCM", 15L)
                .setParameter("heuresTD", 10L)
                .setParameter("heuresTP", 7L)
                .setParameter("UE_ID", 2L)
                .setParameter("ENSEIGNANT_ID", 3L)
                .executeUpdate();
    }
}