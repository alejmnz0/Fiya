package com.app.fiya.field.dto;

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
public class AddField {

    @NotNull(message = "{AddField.name.nonempty}")
    @NotEmpty(message = "{AddField.name.nonempty}")
    @Length(max = 20, message = "{AddField.name.length}")
    private String name;

    @NotNull(message = "{AddField.latitude.nonempty}")
    @NotEmpty(message = "{AddField.latitude.nonempty}")
    private String latitude;

    @NotNull(message = "{AddField.longitude.nonempty}")
    @NotEmpty(message = "{AddField.longitude.nonempty}")
    private String longitude;
}
