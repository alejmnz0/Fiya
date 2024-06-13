package com.app.fiya.appointment.dto;

import com.app.fiya.appointment.model.Appointment;
import com.app.fiya.field.dto.FieldDetailResponse;
import com.app.fiya.field.model.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AppointmentFieldResponse {
    private String fecha;

    public static AppointmentFieldResponse fromAppointment (Appointment data){
        return AppointmentFieldResponse.builder()
                .fecha(data.getStartTime().toString())
                .build();
    }
}
