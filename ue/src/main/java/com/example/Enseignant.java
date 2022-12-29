package com.example;
import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;

// Teacher
@Entity
@Table(name = "ENSEIGNANT")
public class Enseignant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    public Long id;
    public String nom;
    public String prenom;
    public Long heures;
    public float equivalence;
    public String departement;
    public String service;

    public Enseignant() {}
    public void setData(String _nom, String _prenom, Long _heures, float _equivalence, String _departement, String _service)
    {
        nom = _nom;
        prenom = _prenom;
        heures = _heures;
        equivalence = _equivalence;
        departement = _departement;
        service = _service;
    }
}