package com.app.fiya.user.dto;

import com.app.fiya.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
public class UserResponse {

    protected String id;
    protected String image;
    protected String name;
    protected LocalDate birthdate;
    protected String dni;
    protected String rol;

    public static UserResponse fromUser(User user) {

        return UserResponse.builder()
                .id(user.getId().toString())
                .image(user.getImage())
                .name(user.getName())
                .birthdate(user.getBirthdate())
                .dni(user.getDni())
                .rol(user.getRole().toString())
                .build();
    }
}
