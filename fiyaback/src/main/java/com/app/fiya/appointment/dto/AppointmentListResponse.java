package com.app.fiya.appointment.dto;

import com.app.fiya.appointment.model.Appointment;
import com.app.fiya.field.dto.FieldDetailResponse;
import com.app.fiya.field.dto.FieldFavResponse;
import com.app.fiya.field.dto.FieldListResponse;
import com.app.fiya.field.model.Field;
import com.app.fiya.user.dto.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AppointmentListResponse {
    private FieldFavResponse field;
    private UserResponse user;
    private String fecha;

    public static AppointmentListResponse of (Appointment data){
        return AppointmentListResponse.builder()
                .field(FieldFavResponse.of(data.getField()))
                .user(data.getUser() != null ? UserResponse.fromUser(data.getUser()) : null)
                .fecha(data.getStartTime().toString())
                .build();
    }
}
