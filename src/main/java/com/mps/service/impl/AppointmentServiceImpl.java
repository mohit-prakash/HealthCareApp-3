package com.mps.service.impl;

import com.mps.entity.Appointment;
import com.mps.exception.AppointmentNotFoundException;
import com.mps.repository.AppointmentRepository;
import com.mps.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements IAppointmentService {
    @Autowired
    private AppointmentRepository repo;
    @Override
    public Long addAppointment(Appointment appointment) {
        return repo.save(appointment).getAppId();
    }

    @Override
    public Long updateAppointment(Appointment appointment) {
        return repo.save(appointment).getAppId();
    }

    @Override
    public Appointment getAppointmentById(Long appId) {
        return repo.findById(appId).orElseThrow(()->new AppointmentNotFoundException("Appointment "+appId+" does not exist!!"));
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return repo.findAll();
    }

    @Override
    public void deleteAppointmentById(Long appId) {
        repo.delete(getAppointmentById(appId));
    }
}
