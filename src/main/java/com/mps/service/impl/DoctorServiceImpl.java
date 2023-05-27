package com.mps.service.impl;

import com.mps.constants.UserRoles;
import com.mps.entity.Doctor;
import com.mps.entity.User;
import com.mps.exception.DoctorNotFoundException;
import com.mps.repository.DoctorRepository;
import com.mps.service.IDoctorService;
import com.mps.service.IUserService;
import com.mps.util.CollectionUtil;
import com.mps.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DoctorServiceImpl implements IDoctorService {
    @Autowired
    private DoctorRepository repo;
    @Autowired
    private UserUtil userUtil;
    @Autowired
    private IUserService userService;
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
        Long docId = repo.save(doctor).getDocId();
        if (docId!=null){
            User user = new User();
            user.setUsername(doctor.getDocEmailId());
            user.setDisplayName(doctor.getDocName());
            user.setPassword(userUtil.genPwd());
            user.setRole(UserRoles.DOCTOR.name());
            userService.saveUser(user);
        }
        return docId;
    }

    @Override
    public Long updateDoctor(Doctor doctor) {
        String docOldEmailId = repo.findById(doctor.getDocId()).get().getDocEmailId();
        Long docId = repo.save(doctor).getDocId();
        if (docId!=null){
            User user = userService.findByUsername(docOldEmailId).get();
            user.setUsername(doctor.getDocEmailId());
            userService.saveUser(user);
        }
        return docId;
    }

    @Override
    public void deleteDoctorById(Long docId) {
        repo.delete(getDoctorById(docId));
    }

    @Override
    public Map<Long, String> getDocIdAndDocName() {
        return new CollectionUtil().convertObjectArrayToMap(repo.getDocIdAndDocName());
    }

    @Override
    public List<Doctor> getDocBySpecId(Long specId) {
        return repo.getDoctorBySpecId(specId);
    }
}
