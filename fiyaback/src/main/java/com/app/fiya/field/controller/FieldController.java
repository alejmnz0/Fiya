package com.app.fiya.field.controller;

import com.app.fiya.MyPage;
import com.app.fiya.field.dto.AddField;
import com.app.fiya.field.dto.FieldListResponse;
import com.app.fiya.field.service.FieldService;
import com.app.fiya.user.model.User;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/field")
public class FieldController {
    private final FieldService fieldService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Field created successfully", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = User.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "name": "Campo Los corrales",
                                                    "latitude": "37.419068"
                                                    "longitude": "-5.969218"
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Invalid data",
                    content = @Content)
    })
    @PostMapping("/add")
    public ResponseEntity<AddField> addField (@Valid @RequestBody AddField data) {
        fieldService.save(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @GetMapping("/")
    public MyPage<FieldListResponse> getAll (@PageableDefault(page = 0, size = 10) Pageable pageable){
        return fieldService.getAll(pageable);
    }
}
