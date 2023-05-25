package com.mps.repository;

import com.mps.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    @Query("Select docId, docName from Doctor")
    List<Object[]> getDocIdAndDocName();
}
