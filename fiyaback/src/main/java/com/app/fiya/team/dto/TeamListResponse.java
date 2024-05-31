package com.app.fiya.team.dto;

import com.app.fiya.team.model.Team;
import com.app.fiya.user.dto.UserListResponse;
import com.app.fiya.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TeamListResponse {
    private Long id;
    private String name;
    private String primaryColor;
    private String secondaryColor;

    public static TeamListResponse of (Team data){
        return TeamListResponse.builder()
                .id(data.getId())
                .name(data.getName())
                .primaryColor(data.getPrimaryColor().toString())
                .secondaryColor(data.getSecundaryColor().toString())
                .build();
    }
}
