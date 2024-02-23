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
public class UserResponse {

    protected String id;
    protected String dni;
    protected String rol;

    public static UserResponse fromUser(User user) {

        return UserResponse.builder()
                .id(user.getId().toString())
                .dni(user.getDni())
                //.rol((user.getRoles().stream().findFirst().isPresent()) ? user.getRoles().stream().findFirst().get().toString() : "USER" )
                .build();
    }
}
