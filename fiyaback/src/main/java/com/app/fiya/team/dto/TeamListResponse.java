package com.app.fiya.team.dto;

import com.app.fiya.team.model.Team;
import com.app.fiya.user.dto.UserListResponse;
import com.app.fiya.user.dto.UserResponse;
import com.app.fiya.user.dto.UserTeamResponse;
import com.app.fiya.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.awt.color.ColorSpace;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TeamListResponse {
    private Long id;
    private String name;
    private String urlImage;
    private Set<UserTeamResponse> players;
    private int primaryColor;
    private int secondaryColor;

    public static TeamListResponse of (Team data){
        return TeamListResponse.builder()
                .id(data.getId())
                .name(data.getName())
                .urlImage(data.getUrlImage())
                .players(data.getPlayers().stream()
                        .map(UserTeamResponse::fromUser)
                        .collect(Collectors.toSet()))
                .primaryColor(data.getPrimaryColor().getRGB())
                .secondaryColor(data.getSecundaryColor().getRGB())
                .build();

    }
}
