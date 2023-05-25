package com.mps.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "appointment_tab")
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appId", nullable = false)
    private Long appId;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "appDoctor_fk_col")
    private Doctor appDoctor;
    @Column(name = "appDate_col")
    private LocalDate appDate;
    @Column(name = "appSlots_col")
    private Integer appSlots;
    @Column(name = "appDetails_col")
    private String appDetails;
    @Column(name = "appFee_col")
    private Double appFee;
}