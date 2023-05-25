package com.mps.service;

import com.mps.entity.Specialization;

import java.util.List;
import java.util.Map;

public interface ISpecialization {
    List<Specialization> getAllSpecializations();
    Specialization getSpecializationById(Long specId);
    Long addSpecialization(Specialization specialization);
    Long updateSpecialization(Specialization specialization);
    void deleteSpecializationById(Long specId);
    Map<Long,String> getSpecIdAndSpecName();
}
