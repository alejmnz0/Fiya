package com.app.fiya.user.controller;

import com.app.fiya.security.jwt.access.JwtProvider;
import com.app.fiya.user.dto.JwtUserResponse;
import com.app.fiya.user.dto.UserLogin;
import com.app.fiya.user.dto.UserRegister;
import com.app.fiya.user.model.User;
import com.app.fiya.user.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
            @ApiResponse(responseCode = "201", description = "User created successfully", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = User.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "name": "Alejandro Jiménez Fernández",
                                                    "dni": "25435123K"
                                                    "password": "123456789"
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Invalid data",
                    content = @Content)
    })
    @JsonView(UserRegister.UserResponse.class)
    @PostMapping("/register")
    public ResponseEntity<UserRegister> userRegister (@Valid @RequestBody UserRegister data) {
        userService.saveUser(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logged in correctly", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = User.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "id": "f53fd11b-a28f-4748-a725-41c617faf2f3",
                                                                               "email": "user@user.es",
                                                                               "rol": "USER",
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
            @ApiResponse(responseCode = "401",
                    description = "Invalid data",
                    content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<JwtUserResponse> login(@RequestBody UserLogin data) {
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        data.getDni(),
                        data.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED).body(JwtUserResponse.of(user, token));
    }

}
