package com.app.fiya.user.dto;

import com.app.fiya.user.model.User;
import com.app.fiya.validation.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldsValueMatch(
        field = "password", fieldMatch = "repeatPassword",
        message = "{UserRegister.password.nomatch}"
)
public class UserRegister {

    @NotNull(message = "{UserRegister.dni.notempty}")
    @JsonView({UserResponse.class})
    @NotEmpty(message = "{UserRegister.dni.notempty}")
    @UniqueDni
    private String dni;

    @NotEmpty(message = "{UserRegister.email.notempty}")
    @NotNull(message = "{UserRegister.email.notempty}")
    @UniqueEmail
    private String email;

    @NotEmpty(message = "{UserRegister.name.notempty}")
    @JsonView({UserResponse.class})
    @NotNull(message = "{UserRegister.name.notempty}")
    private String name;

    @NotNull(message = "{UserRegister.password.notempty}")
    @JsonView({UserResponse.class})
    @NotEmpty(message = "{UserRegister.password.notempty}")
    @PasswordLength
    private String password;

    @NotNull(message = "{UserRegister.password.notempty}")
    @NotEmpty(message = "{UserRegister.password.notempty}")
    private String repeatPassword;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern =  "dd-MM-yyyy")
    @LocalDateNotNull
    private LocalDate birthdate;

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
