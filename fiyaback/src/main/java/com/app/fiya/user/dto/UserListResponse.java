package com.app.fiya.user.dto;

import com.app.fiya.field.model.Field;
import com.app.fiya.field.model.Ground;
import com.app.fiya.team.dto.TeamListResponse;
import com.app.fiya.team.dto.TeamResponse;
import com.app.fiya.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserListResponse {
    private UUID id;
    private String dni;
    private String name;
    private String email;
    private String image;
    private LocalDate birthdate;
    private TeamResponse team;
    //private Set<Date> dates;

    public static UserListResponse of (User data){
        return UserListResponse.builder()
                .id(data.getId())
                .dni(data.getDni())
                .name(data.getName())
                .email(data.getEmail())
                .image(data.getImage())
                .birthdate(data.getBirthdate())
                .team(data.getTeam() != null ? TeamResponse.of(data.getTeam()) : null)
                .build();
    }
}
