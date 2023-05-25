package com.mps.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "patient_tab")
@Data
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
    @Column(name = "patDob_col")
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    private Date patDob;
    @Column(name = "patMaritalStatus_col")
    private String patMaritalStatus;
    @Column(name = "patEmailId_col")
    private String patEmailId;
    @Column(name = "patPresentAddress_col")
    private String patPresentAddress;
    @Column(name = "patCommunicationAddress_col")
    private String patCommunicationAddress;
    @ElementCollection
    @CollectionTable(name = "patPastMedicalHistory_tab",joinColumns = @JoinColumn(name = "patPastMedicalHistory_fk_col"))
    private Set<String> patPastMedicalHistory;
    @Column(name = "patOtherDetails_col")
    private String patOtherDetails;
}