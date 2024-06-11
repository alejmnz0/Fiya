package com.app.fiya.user.dto;

import com.app.fiya.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
public class UserTeamResponse {
    protected String id;
    protected String email;
    protected String name;
    protected String urlImage;
    protected String dni;

    public static UserTeamResponse fromUser(User user) {

        return UserTeamResponse.builder()
                .id(user.getId().toString())
                .email(user.getEmail())
                .name(user.getName())
                .urlImage(user.getImage())
                .dni(user.getDni())
                .build();
    }
}
