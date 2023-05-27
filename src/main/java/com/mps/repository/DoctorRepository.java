package com.mps.repository;

import com.mps.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    @Query("Select docId, docName from Doctor")
    List<Object[]> getDocIdAndDocName();
    @Query("Select doct from Doctor doct INNER JOIN doct.specialization as spec where spec.specId=:specId")
    List<Doctor> getDoctorBySpecId(Long specId);
}
