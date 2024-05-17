package com.app.fiya.user.controller;

import com.app.fiya.MyPage;
import com.app.fiya.field.model.Field;
import com.app.fiya.security.jwt.access.JwtProvider;
import com.app.fiya.user.dto.*;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User obtained successfully", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = User.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "content": [
                                                        {
                                                            "dni": "12345678A",
                                                            "name": "John Doe",
                                                            "email": "johndoe@gmail.com",
                                                            "image": "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png",
                                                            "birthdate": "2004-06-11"
                                                        },
                                                        {
                                                            "dni": "87654321A",
                                                            "name": "user",
                                                            "email": "user@gmail.com",
                                                            "image": "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png",
                                                            "birthdate": "2001-05-10"
                                                        }
                                                    ],
                                                    "size": 20,
                                                    "elements": 2,
                                                    "page": 0,
                                                    "firs": true,
                                                    "last": true
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content)
    })
    @GetMapping("/")
    public MyPage<UserListResponse> getAll (@PageableDefault(page = 0, size = 20) Pageable pageable){
        return userService.getAll(pageable);
    }

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
                    description = "Invalid data",
                    content = @Content)
    })
    @JsonView(UserRegister.UserResponse.class)
    @PostMapping("/register")
    public ResponseEntity<UserRegister> userRegister (@Valid @RequestBody UserRegister data) {
        userService.saveUser(data);
        String token = login(UserLogin.builder().dni(data.getDni()).password(data.getPassword()).build()).getBody().getToken();
        data.setToken(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = User.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "name": "Alejandro Jiménez Fernández",
                                                    "dni": "25435123K"
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Invalid data",
                    content = @Content)
    })
    @JsonView(UserRegister.AddUserResponse.class)
    @PostMapping("/add")
    public ResponseEntity<UserRegister> addUser (@Valid @RequestBody UserRegister data) {
        userService.saveUser(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User obtained successfully", content = {
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
            @ApiResponse(responseCode = "404",
                    description = "User not found",
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
        return ResponseEntity.status(HttpStatus.OK).body(JwtUserResponse.of(user, token));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = User.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                        "id": "6e625070-1ff9-41d2-b428-1164057bdd0d",
                                                        "image": "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png",
                                                        "email": "user@user.es",
                                                        "name": "User",
                                                        "birthdate": "2004-06-11",
                                                        "dni": "12345678A",
                                                        "rol": "USER"
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Invalid data",
                    content = @Content)
    })
    @GetMapping("/profile")
    public UserResponse getData (@AuthenticationPrincipal User data){
        return UserResponse.fromUser(data);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "true",
                    content = @Content)
    })
    @PostMapping("/{fieldId}/fav")
    public ResponseEntity<?> favourite (@PathVariable Long fieldId, @AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.favourite(fieldId, user));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = User.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                        "id": "6e625070-1ff9-41d2-b428-1164057bdd0d",
                                                        "image": "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png",
                                                        "email": "user@user.es",
                                                        "name": "User",
                                                        "birthdate": "2004-06-11",
                                                        "dni": "12345678A",
                                                        "rol": "USER"
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Invalid data",
                    content = @Content)
    })
    @PostMapping("/edit")
    public ResponseEntity<?> editUser (@RequestBody UserEdit data, @AuthenticationPrincipal User user) {
        userService.editUser(data, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted", content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
