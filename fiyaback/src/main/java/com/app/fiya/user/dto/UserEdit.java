package com.app.fiya.user.dto;

import com.app.fiya.user.model.User;
import com.app.fiya.validation.annotation.LocalDateNotNull;
import com.app.fiya.validation.annotation.PasswordLength;
import com.app.fiya.validation.annotation.UniqueDni;
import com.app.fiya.validation.annotation.UniqueEmail;
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
public class UserEdit {

    @NotEmpty(message = "{UserRegister.email.nonempty}")
    @NotNull(message = "{UserRegister.email.nonempty}")
    @UniqueEmail
    @Email(message = "{UserRegister.email.email}")
    private String email;

    @NotEmpty(message = "{UserRegister.name.nonempty}")
    @NotNull(message = "{UserRegister.name.nonempty}")
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern =  "M/d/yyyy")
    @LocalDateNotNull
    private LocalDate birthdate;

    private boolean isOnTeam;

    public static UserEdit fromUser (User user){
        return UserEdit.builder()
                .name(user.getName())
                .birthdate(user.getBirthdate())
                .email(user.getEmail())
                .isOnTeam(user.isOnTeam())
                .build();
    }
}
