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