package com.mps.repository;

import com.mps.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
    @Query("SELECT specId , specName from Specialization")
    List<Object[]> getSpecIdAndSpecName();
}
