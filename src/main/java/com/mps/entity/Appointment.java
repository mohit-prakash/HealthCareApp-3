package com.mps.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "appointment_tab")
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appId", nullable = false)
    private Long appId;
    // TODO: Change appDoctor from String to Doctor type
    @Column(name = "appDoctor_col")
    private String appDoctor;
    // TODO: Change appDate from String to Date type
    @Column(name = "appDate_col")
    private String appDate;
    @Column(name = "appSlots_col")
    private Integer appSlots;
    @Column(name = "appDetails_col")
    private String appDetails;
    @Column(name = "appFee_col")
    private Double appFee;
}