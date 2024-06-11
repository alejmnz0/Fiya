package com.app.fiya.team.controller;

import com.app.fiya.MyPage;
import com.app.fiya.team.dto.AddPlayer;
import com.app.fiya.team.dto.AddTeam;
import com.app.fiya.team.dto.TeamListResponse;
import com.app.fiya.team.dto.TeamResponse;
import com.app.fiya.team.model.Team;
import com.app.fiya.team.service.TeamService;
import com.app.fiya.user.dto.UserListResponse;
import com.app.fiya.user.dto.UserLogin;
import com.app.fiya.user.dto.UserRegister;
import com.app.fiya.user.dto.UserResponse;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<AddTeam> teamRegister (@Valid @RequestBody AddTeam data, @AuthenticationPrincipal User user) {
        teamService.saveTeam(data, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Team obtained successfully", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Team.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                     "content": [
                                                         {
                                                             "id": 1,
                                                             "name": "Rebaño",
                                                             "primaryColor": -16777216,
                                                             "secondaryColor": -65536
                                                         }
                                                     ],
                                                     "size": 20,
                                                     "elements": 1,
                                                     "page": 0,
                                                     "firs": true,
                                                     "last": true
                                                 }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "Team not found",
                    content = @Content)
    })
    @GetMapping("/")
    public MyPage<TeamListResponse> getAll (@PageableDefault(page = 0, size = 20) Pageable pageable){
        return teamService.getAll(pageable);
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
    public ResponseEntity<AddTeam> addTeam (@Valid @RequestBody AddTeam data) {
        teamService.addTeam(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @PostMapping("/add-player")
    public ResponseEntity<?> addPlayer (@RequestBody AddPlayer data){
        teamService.addPlayer(data);
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
}
