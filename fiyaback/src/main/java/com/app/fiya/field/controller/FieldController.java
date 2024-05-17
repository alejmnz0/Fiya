package com.app.fiya.field.controller;

import com.app.fiya.MyPage;
import com.app.fiya.field.dto.AddField;
import com.app.fiya.field.dto.EditField;
import com.app.fiya.field.dto.FieldDetailResponse;
import com.app.fiya.field.dto.FieldListResponse;
import com.app.fiya.field.model.Field;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/field")
public class FieldController {
    private final FieldService fieldService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Field created successfully", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Field.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "name": "Campo nuevo",
                                                    "latitude": "37.419068",
                                                    "longitude": "-5.969218",
                                                    "price": 12.0,
                                                    "teamCapacity": 7,
                                                    "ground": "CLAY"
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fields obtained successfully", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Field.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "content": [
                                                            {
                                                                "id": 1,
                                                                "name": "Los corrales futbol",
                                                                "latitude": "123",
                                                                "longitude": "321",
                                                                "price": 40.0,
                                                                "teamCapacity": 7,
                                                                "ground": "CLAY"
                                                            },
                                                            {
                                                                "id": 2,
                                                                "name": "Campo de Futbol Los mares",
                                                                "latitude": "3123",
                                                                "longitude": "3213",
                                                                "price": 40.0,
                                                                "teamCapacity": 7,
                                                                "ground": "CONCRETE"
                                                            }
                                                        ],
                                                        "size": 20,
                                                        "elements": 11,
                                                        "page": 0,
                                                        "firs": true,
                                                        "last": true
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "Fields not found",
                    content = @Content)
    })
    @GetMapping("/")
    public MyPage<FieldListResponse> getAll (@PageableDefault(page = 0, size = 20) Pageable pageable){
        return fieldService.getAll(pageable);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Field obtained successfully", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Field.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "id": 1,
                                                    "name": "Los corrales futbol",
                                                    "latitude": "123",
                                                    "longitude": "321",
                                                    "price": 40.0,
                                                    "teamCapacity": 7,
                                                    "ground": "CLAY",
                                                    "description": null
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "Field not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<FieldDetailResponse> getField (@PathVariable Long id){
        FieldDetailResponse data = fieldService.getFieldById(id);
        URI createdUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(data.getId()).toUri();

        return ResponseEntity.ok().body(data);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fields obtained successfully", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Field.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "content": [
                                                                               {
                                                                                   "id": 1,
                                                                                   "name": "Los corrales futbol",
                                                                                   "latitude": "123",
                                                                                   "longitude": "321",
                                                                                   "price": 40.0,
                                                                                   "teamCapacity": 7,
                                                                                   "ground": "CLAY"
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
                    description = "Fields not found",
                    content = @Content)
    })
    @GetMapping("/favourites")
    public MyPage<FieldListResponse> getFavFieldsByUser (@PageableDefault(page = 0, size = 20) Pageable pageable, @AuthenticationPrincipal User user){
        return fieldService.getFavFieldsByUser(pageable, user);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Field deleted", content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteField(@PathVariable Long id){
        fieldService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Field edited successfully", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Field.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "name": "Campo nuevo",
                                        "latitude": "37.419068",
                                        "longitude": "-5.969218",
                                        "price": 12.0,
                                        "teamCapacity": 7,
                                        "ground": "CLAY"
                                    }
                                    """) }) }),
            @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content),
    })
    @PutMapping("/{id}/edit")
    public ResponseEntity<EditField> editField (@PathVariable Long id, @Valid @RequestBody EditField field){
        fieldService.edit(id, field);
        return ResponseEntity.status(HttpStatus.OK).body(field);
    }


}
