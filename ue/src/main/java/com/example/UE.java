package com.example;

import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "UE")
public class UE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    public Long id;
    public String nom;
    public Long semestre;
    public Long heuresCM;
    public Long heuresTD;
    public Long heuresTP;

    public Long groupesCM;
    public Long groupesTD;
    public Long groupesTP;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Enseignant.class)
    @JoinTable(name = "UE_ENSEIGNANT",
            joinColumns = @JoinColumn(name = "UE_ID", foreignKey = @ForeignKey(name = "FK_UE")),
            inverseJoinColumns = @JoinColumn(name = "ENSEIGNANT_ID", foreignKey = @ForeignKey(name = "FK_ENSEIGNANT")))
    public List enseignants;

    public UE() {

    }

    public void setData(String _nom, Long _semestre, Long _heuresCM, Long _heuresTD, Long _heuresTP, Long _groupesCM, Long _groupesTD, Long _groupesTP, List _enseignants)
    {
        nom = _nom;
        semestre = _semestre;
        heuresCM = _heuresCM;
        heuresTD = _heuresTD;
        heuresTP = _heuresTP;
        groupesCM = _groupesCM;
        groupesTD = _groupesTD;
        groupesTP = _groupesTP;
        enseignants = _enseignants;
    }
}