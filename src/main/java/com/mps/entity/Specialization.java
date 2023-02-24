package com.mps.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "specialization_tab")
@Data
public class Specialization {

    @Column(name = "specId_col")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long specId;
    @Column(name = "specCode_col")
    private String specCode;
    @Column(name = "specName_col")
    private String specName;
    @Column(name = "specNote_col")
    private String specNote;
}