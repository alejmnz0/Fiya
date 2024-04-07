package com.app.fiya.user.dto;

import com.app.fiya.MyPage;
import com.app.fiya.field.dto.FieldFavResponse;
import com.app.fiya.field.dto.FieldListResponse;
import com.app.fiya.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
public class UserResponse {

    protected String id;
    protected String image;
    protected String email;
    protected String name;
    protected LocalDate birthdate;
    protected Set<FieldFavResponse> favourites;
    protected boolean isOnTeam;
    protected String dni;
    protected String rol;

    public static UserResponse fromUser(User user) {

        return UserResponse.builder()
                .id(user.getId().toString())
                .image(user.getImage())
                .email(user.getEmail())
                .name(user.getName())
                .birthdate(user.getBirthdate())
                .dni(user.getDni())
                .rol(user.getRole().toString())
                .favourites(user.getFavourites().stream().map(FieldFavResponse::of).collect(Collectors.toSet()))
                .isOnTeam(user.isOnTeam())
                .build();
    }
}
