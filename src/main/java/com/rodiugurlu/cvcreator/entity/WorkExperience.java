package com.rodiugurlu.cvcreator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String position;
    private String year;
    private String company;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cv_id", nullable = false)
    private Cv cv;



}

