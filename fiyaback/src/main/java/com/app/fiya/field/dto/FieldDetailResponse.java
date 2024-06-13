package com.app.fiya.field.dto;

import com.app.fiya.appointment.dto.AppointmentFieldResponse;
import com.app.fiya.field.model.Field;
import com.app.fiya.rent.dto.RentResponse;
import com.app.fiya.rent.model.Rent;
import com.app.fiya.user.dto.UserTeamResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class FieldDetailResponse {
    private Long id;
    private String name;
    private String latitude;
    private String longitude;
    private double price;
    private int teamCapacity;
    private String ground;
    private String description;
    private Set<AppointmentFieldResponse> appointments;
    //private Set<RentResponse> renting;

    public static FieldDetailResponse of (Field data){
        return FieldDetailResponse.builder()
                .id(data.getId())
                .name(data.getName())
                .latitude(data.getLatitude())
                .longitude(data.getLongitude())
                .price(data.getPrice())
                .teamCapacity(data.getTeamCapacity())
                .ground(data.getGround().toString())
                .description(data.getDescription())
                .appointments(data.getAppointments().stream()
                        .map(AppointmentFieldResponse::fromAppointment)
                        .collect(Collectors.toSet()))
                .build();
    }
}
