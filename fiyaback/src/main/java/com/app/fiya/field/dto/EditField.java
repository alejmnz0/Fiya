package com.app.fiya.field.dto;

import com.app.fiya.field.model.Ground;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EditField {

    @Length(max = 50, message = "{AddField.name.length}")
    private String name;

    private String latitude;

    private String longitude;

    @Min(value = 0)
    private double price;

    @Min(value = 0)
    private int teamCapacity;

    private Ground ground;

    @Length(max = 100, message = "{AddField.description.length}")
    private String description;
}
