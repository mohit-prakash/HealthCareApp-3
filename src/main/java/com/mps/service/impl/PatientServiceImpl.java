package com.mps.service.impl;

import com.mps.entity.Patient;
import com.mps.exception.PatientNotFoundException;
import com.mps.repository.PatientRepository;
import com.mps.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PatientServiceImpl implements IPatientService {
    @Autowired
    private PatientRepository repo;

    @Override
    public Long addPatient(Patient patient) {
        return repo.save(patient).getPatId();
    }

    @Override
    public Long updatePatient(Patient patient) {
        return repo.save(patient).getPatId();
    }

    @Override
    public Patient getPatientById(Long patId) {
        return repo.findById(patId).orElseThrow(()->new PatientNotFoundException("Patent Id "+patId+" does not exist!!"));
    }

    @Override
    public List<Patient> getAllPatients() {
        return repo.findAll();
    }

    @Override
    public void deletePatientById(Long patId) {
        repo.delete(getPatientById(patId));
    }
}
