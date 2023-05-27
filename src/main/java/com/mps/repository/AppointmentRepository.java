package com.mps.repository;

import com.mps.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("Select aptmt.appDate, aptmt.appSlots,aptmt.appFee From Appointment aptmt INNER JOIN aptmt.appDoctor as doct where doct.docId=:docId")
    List<Object[]> getAppointmentByDocId(Long docId);
}