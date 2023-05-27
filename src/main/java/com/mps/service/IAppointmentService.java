package com.mps.service;

import com.mps.entity.Appointment;

import java.util.List;

public interface IAppointmentService {
    Long addAppointment(Appointment appointment);
    Long updateAppointment(Appointment appointment);
    Appointment getAppointmentById(Long appId);
    List<Appointment> getAllAppointments();
    void deleteAppointmentById(Long appId);
    List<Object[]> getAppointmentByDocId(Long docId);
}
