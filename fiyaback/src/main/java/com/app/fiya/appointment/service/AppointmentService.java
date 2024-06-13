package com.app.fiya.appointment.service;

import com.app.fiya.MyPage;
import com.app.fiya.appointment.dto.AppointmentListResponse;
import com.app.fiya.appointment.model.Appointment;
import com.app.fiya.appointment.repository.AppointmentRepository;
import com.app.fiya.exception.NotFoundException;
import com.app.fiya.field.dto.FieldListResponse;
import com.app.fiya.field.model.Field;
import com.app.fiya.field.repository.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final FieldRepository fieldRepository;

    @Transactional
    public void generateWeeklyAppointments() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(1);

        for (Field field : fieldRepository.findAll()) {
            generateAppointmentsForField(field, startDate, endDate);
        }
    }

    private void generateAppointmentsForField(Field field, LocalDate startDate, LocalDate endDate) {
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            if (date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                generateAppointmentsForFieldAndDay(field, date);
            }
        }
    }

    private void generateAppointmentsForFieldAndDay(Field field, LocalDate date) {
        LocalTime startTime = LocalTime.of(10, 0);
        LocalTime endTime = LocalTime.of(11, 0);

        for (LocalTime time = startTime; !time.isAfter(endTime); time = time.plusHours(1)) {
            LocalDateTime appointmentTime = LocalDateTime.of(date, time);
            if (!appointmentExists(field, appointmentTime)) {
                Appointment appointment = new Appointment();
                appointment.setField(field);
                appointment.setStartTime(appointmentTime);
                appointmentRepository.save(appointment);
            }
        }
    }

    private boolean appointmentExists(Field field, LocalDateTime startTime) {
        return appointmentRepository.existsByFieldAndStartTime(field, startTime);
    }

    @Transactional
    public List<Appointment> getAppointmentsForField(Long fieldId) {
        Optional<Field> optionalField = fieldRepository.findById(fieldId);
        if (optionalField.isPresent()) {
            return optionalField.get().getAppointments();
        } else {
            throw new NotFoundException("Field");
        }
    }

    public MyPage<AppointmentListResponse> getAll(Pageable pageable) {
        Page<Appointment> result = appointmentRepository.findAll(pageable);
        if (result.isEmpty())
            throw new NotFoundException("Field");
        Page<AppointmentListResponse> response = result.map(AppointmentListResponse::of);
        return MyPage.of(response);
    }
}
