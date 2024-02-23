package com.app.fiya.user.dto;

import com.app.fiya.user.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtUserResponse extends UserResponse {

    private String token;

    public JwtUserResponse(UserResponse userResponse) {
        id = userResponse.getId();
        dni = userResponse.getDni();
        rol = userResponse.getRol();
    }


    public static JwtUserResponse of (User user, String token) {
        JwtUserResponse result = new JwtUserResponse(UserResponse.fromUser(user));
        result.setToken(token);
        return result;
    }
}
