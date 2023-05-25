package com.mps.service;

import com.mps.entity.Doctor;

import java.util.List;
import java.util.Map;

public interface IDoctorService {

    List<Doctor> getAllDoctors();
    Doctor getDoctorById(Long docId);
    Long addDoctor(Doctor doctor);
    Long updateDoctor(Doctor doctor);
    void deleteDoctorById(Long docId);
    Map<Long,String> getDocIdAndDocName();
}
