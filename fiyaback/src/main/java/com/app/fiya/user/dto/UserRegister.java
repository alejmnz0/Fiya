package com.app.fiya.user.dto;

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
public class UserRegister {

    private UUID id;

    @NotNull
    @NotEmpty
    private String dni;

    @NotEmpty
    @NotNull
    private String email;

    @NotEmpty
    @NotNull
    private String name;

    @NotNull
    @NotEmpty
    private String password;

    @NotEmpty
    private String repeatPassword;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern =  "dd-MM-yyyy")
    private LocalDate birthdate;

}
