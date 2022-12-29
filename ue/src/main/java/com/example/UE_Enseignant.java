package com.example;

import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "UE_ENSEIGNANT")
class UE_Enseignant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    public Long id;
    public Long UE_ID;
    public Long ENSEIGNANT_ID;

    public Long heuresCM;
    public Long heuresTD;
    public Long heuresTP;
}

@Entity
@Table(name = "UE")
class UE {
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
    public List<Enseignant> enseignants;

    public UE() {}

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

    public String enseignantsJSON()
    {
        String result = "[\n";
        for (int i = 0; i < enseignants.size(); i++)
        {
            result += "\t\t" + enseignants.get(i).toJSON() + ",\n";
        }
        result = result + "\t]";
        return result;
    }


    public String toJSON()
    {
        return "{" +
                "\"id\": " + id +
                ", \"nom\": \"" + nom + "\"" +
                ", \"semestre\": " + semestre +
                ", \"heuresCM\": " + heuresCM +
                ", \"heuresTD\": " + heuresTD +
                ", \"heuresTP\": " + heuresTP +
                ", \"groupesCM\": " + groupesCM +
                ", \"groupesTD\": " + groupesTD +
                ", \"groupesTP\": " + groupesTP +
                "}";
    }

    public String toFullJSON()
    {
        return "{" +
                "\n\t\"id\": " + id +
                ",\n\t\"nom\": \"" + nom + "\"" +
                ",\n\t\"semestre\": " + semestre +
                ",\n\t\"heuresCM\": " + heuresCM +
                ",\n\t\"heuresTD\": " + heuresTD +
                ",\n\t\"heuresTP\": " + heuresTP +
                ",\n\t\"groupesCM\": " + groupesCM +
                ",\n\t\"groupesTD\": " + groupesTD +
                ",\n\t\"groupesTP\": " + groupesTP +
                ",\n\t\"enseignants\": " + enseignantsJSON() +
                "\n}";
    }
}

@Entity
@Table(name = "ENSEIGNANT")
class Enseignant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    public Long id;
    public String nom;
    public String prenom;
    public Long heures;
    public float equivalence;
    public String departement;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = UE.class)
    @JoinTable(name = "UE_ENSEIGNANT",
            joinColumns = @JoinColumn(name = "ENSEIGNANT_ID", foreignKey = @ForeignKey(name = "FK_ENSEIGNANT")),
            inverseJoinColumns = @JoinColumn(name = "UE_ID", foreignKey = @ForeignKey(name = "FK_UE")))
    public List<UE> service;

    public Enseignant() {}
    public void setData(String _nom, String _prenom, Long _heures, float _equivalence, String _departement, List _service)
    {
        nom = _nom;
        prenom = _prenom;
        heures = _heures;
        equivalence = _equivalence;
        departement = _departement;
        service = _service;
    }

    public String serviceJSON()
    {
        String result = "[\n";
        for (int i = 0; i < service.size(); i++)
        {
            result += "\t\t" + service.get(i).toJSON() + ",\n";
        }
        result = result.substring(0, result.length()-2) + "\n\t]";
        return result;
    }

    public String toJSON()
    {
        return "{" +
                ", \"id\": " + id +
                ", \"prenom\": \"" + prenom + "\"" +
                ", \"nom\": \"" + nom + "\"" +
                ", \"heures\": " + heures +
                ", \"equivalence\": " + equivalence +
                ", \"departement\": \"" + departement + "\"" +
                "}";
    }

    public String toFullJSON()
    {
        return "{" +
                "\n\t\"id\": " + id +
                ",\n\t\"prenom\": \"" + prenom + "\"" +
                ",\n\t\"nom\": \"" + nom + "\"" +
                ",\n\t\"heures\": " + heures +
                ",\n\t\"equivalence\": " + equivalence +
                ",\n\t\"departement\": \"" + departement + "\"" +
                ",\n\t\"service\": " + serviceJSON() +
                "\n}";
    }
}