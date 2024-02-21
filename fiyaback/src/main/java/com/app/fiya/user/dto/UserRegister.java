package com.app.fiya.user.dto;

import com.app.fiya.validation.annotation.FieldsValueMatch;
import com.app.fiya.validation.annotation.PasswordLength;
import com.app.fiya.validation.annotation.UniqueDni;
import com.app.fiya.validation.annotation.UniqueEmail;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @NotEmpty(message = "{UserRegister.dni.notempty}")
    @UniqueDni
    private String dni;

    @NotEmpty(message = "{UserRegister.email.notempty}")
    @NotNull(message = "{UserRegister.email.notempty}")
    @UniqueEmail
    private String email;

    @NotEmpty(message = "{UserRegister.name.notempty}")
    @NotNull(message = "{UserRegister.name.notempty}")
    private String name;

    @NotNull(message = "{UserRegister.password.notempty}")
    @NotEmpty(message = "{UserRegister.password.notempty}")
    @PasswordLength
    private String password;

    @NotNull(message = "{UserRegister.password.notempty}")
    @NotEmpty(message = "{UserRegister.password.notempty}")
    private String repeatPassword;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern =  "dd-MM-yyyy")
    @NotNull(message = "{UserRegister.bithdate.notempty}")
    @NotEmpty(message = "{UserRegister.bithdate.notempty}")
    private LocalDate birthdate;

}
