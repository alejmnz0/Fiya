package com.app.fiya.team.controller;

import com.app.fiya.team.dto.AddTeam;
import com.app.fiya.team.dto.TeamResponse;
import com.app.fiya.team.model.Team;
import com.app.fiya.team.service.TeamService;
import com.app.fiya.user.dto.UserLogin;
import com.app.fiya.user.dto.UserRegister;
import com.app.fiya.user.model.User;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Team created successfully", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Team.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "name": "El rebaño",
                                                    "urlImage": "https://www.absoluteeventmanagement.com/wp-content/uploads/2018/06/person-placeholder.jpg"
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Invalid data",
                    content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<AddTeam> userRegister (@Valid @RequestBody AddTeam data, @AuthenticationPrincipal User user) {
        teamService.saveTeam(data, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }
}
