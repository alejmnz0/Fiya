package com.app.fiya.field.dto;

import com.app.fiya.date.model.Date;
import com.app.fiya.field.model.Field;
import com.app.fiya.field.model.Ground;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class FieldListResponse {
    private Long id;
    private String name;
    private String latitude;
    private String longitude;
    private double price;
    private int teamCapacity;
    private String ground;
    //private Set<Date> dates;

    public static FieldListResponse of (Field data){
        return FieldListResponse.builder()
                .id(data.getId())
                .name(data.getName())
                .latitude(data.getLatitude())
                .longitude(data.getLongitude())
                .price(data.getPrice())
                .teamCapacity(data.getTeamCapacity())
                .ground(data.getGround().toString())
                //.dates(data.getDates())
                .build();
    }
}
