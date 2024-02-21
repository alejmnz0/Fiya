package com.app.fiya.user.controller;

import com.app.fiya.security.jwt.access.JwtProvider;
import com.app.fiya.user.dto.UserRegister;
import com.app.fiya.user.model.User;
import com.app.fiya.user.service.UserService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successfully registered user", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = User.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "id": "f53fd11b-a28f-4748-a725-41c617faf2f3",
                                                                               "username": "Pepeillo",
                                                                               "rol": "ADMIN",
                                                                               "token": "eyJ0eXAiOiJKV1
                                                                               QiLCJhbGciOiJIUzUxMiJ9.eyJ
                                                                               zdWIiOiJmNTNmZDExYi1hMjhmLT
                                                                               Q3NDgtYTcyNS00MWM2MTdmYWYy
                                                                               ZjMiLCJpYXQiOjE3MDExOTgzNTg
                                                                               sImV4cCI6MTcwMTI4NDc1OH0.aQ
                                                                               6EYXHlHcUXAjKzSMXsXKOtpac3O
                                                                               Lrw9VkAYb31PtmRea8X2RrRzMci8k
                                                                               A-_BRG6U6Y9rX3Jyc8s0jXM8rbMw"
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Los datos introducidos no son válidos",
                    content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<UserRegister> userRegister (@Valid @RequestBody UserRegister data){
        userService.saveUser(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

}
