package com.app.fiya.field.dto;

import com.app.fiya.field.model.Field;
import com.app.fiya.rent.dto.RentResponse;
import com.app.fiya.rent.model.Rent;
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
                //.renting(data.getRenting().stream().map(RentResponse::of).collect(Collectors.toSet()))
                .build();
    }
}
