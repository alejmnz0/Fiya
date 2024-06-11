package com.app.fiya.team.dto;

import com.app.fiya.team.model.Team;
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
public class TeamResponse {

    private String name;
    private String urlImage;

    public static TeamResponse of (Team data){
        return TeamResponse.builder()
                .name(data.getName())
                .urlImage(data.getUrlImage())
                .build();
    }
}
