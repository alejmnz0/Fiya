package com.app.fiya.rent.dto;

import com.app.fiya.field.dto.FieldDetailResponse;
import com.app.fiya.field.model.Field;
import com.app.fiya.rent.model.Rent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RentResponse {
    private LocalDateTime rentTime;
    private Long id;

    public static RentResponse of (Rent data){
        return RentResponse.builder()
                .rentTime(data.getRentTime())
                .id(data.getId())
                .build();
    }
}
