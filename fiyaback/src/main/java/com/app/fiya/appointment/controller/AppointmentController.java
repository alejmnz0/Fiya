package com.app.fiya.appointment.controller;

import com.app.fiya.MyPage;
import com.app.fiya.appointment.dto.AppointmentListResponse;
import com.app.fiya.appointment.service.AppointmentService;
import com.app.fiya.field.dto.FieldListResponse;
import com.app.fiya.field.model.Field;
import com.app.fiya.field.service.FieldService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointment obtained successfully", content = {
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
    public MyPage<AppointmentListResponse> getAll (@PageableDefault(page = 0, size = 20) Pageable pageable){
        return appointmentService.getAll(pageable);
    }
}
