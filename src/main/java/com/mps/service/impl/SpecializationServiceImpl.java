package com.mps.service.impl;

import com.mps.entity.Specialization;
import com.mps.exception.SpecializationNotFoundException;
import com.mps.repository.SpecializationRepository;
import com.mps.service.ISpecialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializationServiceImpl implements ISpecialization {
    @Autowired
    private SpecializationRepository repo;

    @Override
    public List<Specialization> getAllSpecializations() {
        return repo.findAll();
    }

    @Override
    public Specialization getSpecializationById(Long specId) {
        return repo.findById(specId).orElseThrow(()->new SpecializationNotFoundException("Specid "+specId+" not found"));
    }

    @Override
    public Long addSpecialization(Specialization specialization) {
        return repo.save(specialization).getSpecId();
    }

    @Override
    public Long updateSpecialization(Specialization specialization) {
        return repo.save(specialization).getSpecId();
    }

    @Override
    public void deleteSpecializationById(Long specId) {
        repo.delete(getSpecializationById(specId));
    }
}
