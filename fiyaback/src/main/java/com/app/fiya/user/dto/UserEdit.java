package com.app.fiya.user.dto;

import com.app.fiya.user.model.User;
import com.app.fiya.validation.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ExeptionUniqueEmail(
        actualEmailField = "actualEmail", newEmailField = "email"
)
public class UserEdit {

    @NotEmpty(message = "{UserRegister.email.nonempty}")
    @NotNull(message = "{UserRegister.email.nonempty}")
    @Email(message = "{UserRegister.email.email}")
    @JsonView(EditUserResponse.class)
    private String email;

    private String actualEmail;

    @NotEmpty(message = "{UserRegister.name.nonempty}")
    @NotNull(message = "{UserRegister.name.nonempty}")
    @JsonView(EditUserResponse.class)
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern =  "M/d/yyyy")
    @LocalDateNotNull
    @JsonView(EditUserResponse.class)
    private LocalDate birthdate;

    private boolean isOnTeam;

    public static UserEdit fromUser (User user){
        return UserEdit.builder()
                .name(user.getName())
                .birthdate(user.getBirthdate())
                .email(user.getEmail())
                .actualEmail(user.getEmail())
                .isOnTeam(user.isOnTeam())
                .build();
    }

    public static class EditUserResponse {}
}
