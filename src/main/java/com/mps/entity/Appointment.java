package com.mps.entity;

import javax.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    private Date appDate;
    @Column(name = "appSlots_col")
    private Integer appSlots;
    @Column(name = "appDetails_col")
    private String appDetails;
    @Column(name = "appFee_col")
    private Double appFee;
}