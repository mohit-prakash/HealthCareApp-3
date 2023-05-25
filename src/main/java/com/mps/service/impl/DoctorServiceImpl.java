package com.mps.service.impl;

import com.mps.entity.Doctor;
import com.mps.exception.DoctorNotFoundException;
import com.mps.repository.DoctorRepository;
import com.mps.service.IDoctorService;
import com.mps.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DoctorServiceImpl implements IDoctorService {
    @Autowired
    private DoctorRepository repo;
    @Override
    public List<Doctor> getAllDoctors() {
        return repo.findAll();
    }

    @Override
    public Doctor getDoctorById(Long docId) {
        return repo.findById(docId).orElseThrow(()->new DoctorNotFoundException("DocId "+docId+" not found!!"));
    }

    @Override
    public Long addDoctor(Doctor doctor) {
        return repo.save(doctor).getDocId();
    }

    @Override
    public Long updateDoctor(Doctor doctor) {
        return repo.save(doctor).getDocId();
    }

    @Override
    public void deleteDoctorById(Long docId) {
        repo.delete(getDoctorById(docId));
    }

    @Override
    public Map<Long, String> getDocIdAndDocName() {
        return new CollectionUtil().convertObjectArrayToMap(repo.getDocIdAndDocName());
    }
}
