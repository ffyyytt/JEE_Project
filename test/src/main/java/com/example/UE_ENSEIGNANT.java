package com.example;

import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
class Ue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    public Long id;

    @OneToMany(mappedBy = "ue", fetch = FetchType.LAZY)
    public List<UeEnseignant> ueEnseignant = new ArrayList<UeEnseignant>();

    public String nom;
    public Long semestre;
    public Long heuresCM;
    public Long heuresTD;
    public Long heuresTP;

    public Long groupesCM;
    public Long groupesTD;
    public Long groupesTP;

    public Ue(){};

    public void setData(String _nom, Long _semestre, Long _heuresCM, Long _heuresTD, Long _heuresTP, Long _groupesCM, Long _groupesTD, Long _groupesTP, List _ueEnseignant)
    {
        nom = _nom;
        semestre = _semestre;
        heuresCM = _heuresCM;
        heuresTD = _heuresTD;
        heuresTP = _heuresTP;
        groupesCM = _groupesCM;
        groupesTD = _groupesTD;
        groupesTP = _groupesTP;
        ueEnseignant = _ueEnseignant;
    }
}

@Entity
class UeEnseignant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    public Long id;

    @ManyToOne()
    @JoinColumn(foreignKey = @ForeignKey(name = "ue_id"))
    private Ue ue;

    @ManyToOne()
    @JoinColumn(foreignKey = @ForeignKey(name = "enseignant_code"))
    private Enseignant enseignant;

    public Long heuresCM;
    public Long heuresTD;
    public Long heuresTP;

    public UeEnseignant(){};
    public void setData(Long _heuresCM, Long _heuresTD, Long _heuresTP)
    {
        heuresCM = _heuresCM;
        heuresTD = _heuresTD;
        heuresTP = _heuresTP;
    }

    public void setUe(Ue _ue)
    {
        ue = _ue;
    }

    public void setEnseignant(Enseignant _enseignant)
    {
        enseignant = _enseignant;
    }
}

@Entity
class Enseignant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    public Long id;

    @OneToMany(mappedBy = "enseignant", fetch = FetchType.LAZY)
    public List<UeEnseignant> userServices = new ArrayList<UeEnseignant>();

    public String nom;
    public String prenom;
    public Long heures;
    public float equivalence;
    public String departement;
    public String service;

    public Enseignant() {}
    public void setData(String _nom, String _prenom, Long _heures, float _equivalence, String _departement, String _service, List _userServices)
    {
        nom = _nom;
        prenom = _prenom;
        heures = _heures;
        equivalence = _equivalence;
        departement = _departement;
        service = _service;
        userServices = _userServices;
    }
}