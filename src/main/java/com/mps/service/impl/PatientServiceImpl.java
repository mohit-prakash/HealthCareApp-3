package com.mps.service.impl;

import com.mps.constants.UserRoles;
import com.mps.entity.Patient;
import com.mps.entity.User;
import com.mps.exception.PatientNotFoundException;
import com.mps.repository.PatientRepository;
import com.mps.service.IPatientService;
import com.mps.service.IUserService;
import com.mps.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements IPatientService {
    @Autowired
    private PatientRepository repo;
    @Autowired
    private UserUtil userUtil;
    @Autowired
    private IUserService userService;

    @Override
    public Long addPatient(Patient patient) {
        Long patId = repo.save(patient).getPatId();
        if (patId!=null){
            User user =  new User();
            user.setUsername(patient.getPatEmailId());
            user.setDisplayName(patient.getPatFirstName()+" "+patient.getPatLastName());
            user.setPassword(userUtil.genPwd());
            user.setRole(UserRoles.PATIENT.name());
            userService.saveUser(user);
        }
        return patId;
    }

    @Override
    public Long updatePatient(Patient patient) {
        String patOldEmailId = repo.findById(patient.getPatId()).get().getPatEmailId();
        Long patId = repo.save(patient).getPatId();
        if (patId!=null){
            User user = userService.findByUsername(patOldEmailId).get();
            user.setUsername(patient.getPatEmailId());
            userService.saveUser(user);
        }
        return patId;
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
