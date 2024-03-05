package com.app.fiya.field.dto;

import com.app.fiya.field.model.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class FieldFavResponse {

    private Long id;
    private String name;
    private double price;
    private int teamCapacity;
    private String ground;

    public static FieldFavResponse of (Field data){
        return FieldFavResponse.builder()
                .id(data.getId())
                .name(data.getName())
                .price(data.getPrice())
                .teamCapacity(data.getTeamCapacity())
                .ground(data.getGround().toString())
                .build();
    }
}
