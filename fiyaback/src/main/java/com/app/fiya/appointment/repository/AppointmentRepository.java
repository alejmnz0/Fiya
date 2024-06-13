package com.app.fiya.appointment.repository;

import com.app.fiya.appointment.model.Appointment;
import com.app.fiya.field.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    boolean existsByFieldAndStartTime(Field field, LocalDateTime startTime);
}
