package com.mps.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity //for entity class
@Table(name = "patient_tab")
@Data //lombok
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patId_col", nullable = false)
    private Long patId;
    @Column(name = "patFirstName_col")
    private String patFirstName;
    @Column(name = "patLastName_col")
    private String patLastName;
    @Column(name = "patGender_col")
    private String patGender;
    @Column(name = "patPhone_col")
    private String patPhone;
    //TODO: Change patDob String to Date
    @Column(name = "patDob_col")
    private String patDob;
    @Column(name = "patMaritalStatus_col")
    private String patMaritalStatus;
    @Column(name = "patEmailId_col")
    private String patEmailId;
    @Column(name = "patPresentAddress_col")
    private String patPresentAddress;
    @Column(name = "patCommunicationAddress_col")
    private String patCommunicationAddress;
    //TODO: Change patPastMedicalHistory String to Collection type
    @Column(name = "patPastMedicalHistory_col")
    private String patPastMedicalHistory;
    @Column(name = "patOtherDetails_col")
    private String patOtherDetails;
}