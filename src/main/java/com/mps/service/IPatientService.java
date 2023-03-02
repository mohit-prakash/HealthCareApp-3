package com.mps.service;

import com.mps.entity.Patient;

import java.util.List;

public interface IPatientService {
    Long addPatient(Patient patient);
    Long updatePatient(Patient patient);
    Patient getPatientById(Long patId);
    List<Patient> getAllPatients();
    void deletePatientById(Long patId);
}
