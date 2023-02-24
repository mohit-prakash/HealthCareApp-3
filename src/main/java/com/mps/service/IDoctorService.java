package com.mps.service;

import com.mps.entity.Doctor;

import java.util.List;

public interface IDoctorService {

    List<Doctor> getAllDoctors();
    Doctor getDoctorById(Long docId);
    Long addDoctor(Doctor doctor);
    Long updateDoctor(Doctor doctor);
    void deleteDoctorById(Long docId);

}
