package com.mps.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "doctor_tab")
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "docId_col", nullable = false)
    private Long docId;
    @Column(name="docName_col")
    private String docName;
    @Column(name="docEmailId_col")
    private String docEmailId;
    @Column(name="docSpecialization_col")
    private String docSpecialization;
    @Column(name="docAddress_col")
    private String docAddress;
    @Column(name="docMobile_col")
    private String docMobile;
    @Column(name="docGender_col")
    private String docGender;
    @Column(name="docNote_col")
    private String docNote;
    @Column(name="docPhoto_col")
    private String docPhoto;
}