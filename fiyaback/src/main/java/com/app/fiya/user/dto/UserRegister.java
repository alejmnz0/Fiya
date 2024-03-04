package com.app.fiya.user.dto;

import com.app.fiya.user.model.User;
import com.app.fiya.validation.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldsValueMatch(
        field = "password", fieldMatch = "repeatPassword",
        message = "{UserRegister.password.match}"
)
public class UserRegister {

    @NotNull(message = "{UserRegister.dni.nonempty}")
    @JsonView({UserResponse.class})
    @NotEmpty(message = "{UserRegister.dni.nonempty}")
    @UniqueDni
    private String dni;

    @NotEmpty(message = "{UserRegister.email.nonempty}")
    @NotNull(message = "{UserRegister.email.nonempty}")
    @UniqueEmail
    @Email(message = "{UserRegister.email.email}")
    private String email;

    @NotEmpty(message = "{UserRegister.name.nonempty}")
    @JsonView({UserResponse.class})
    @NotNull(message = "{UserRegister.name.nonempty}")
    private String name;

    @NotNull(message = "{UserRegister.password.nonempty}")
    @JsonView({UserResponse.class})
    @NotEmpty(message = "{UserRegister.password.nonempty}")
    @PasswordLength
    private String password;

    @NotNull(message = "{UserRegister.password.nonempty}")
    @NotEmpty(message = "{UserRegister.password.nonempty}")
    private String repeatPassword;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern =  "M/d/yyyy")
    @LocalDateNotNull
    private LocalDate birthdate;

    @JsonView({UserResponse.class})
    private String token;

    public static UserRegister fromUser (User user){
        return UserRegister.builder()
                .name(user.getName())
                .dni(user.getDni())
                .birthdate(user.getBirthdate())
                .email(user.getEmail())
                .password(user.getPassword())
                .repeatPassword(user.getPassword())
                .build();
    }

    public static class UserResponse {}

}
