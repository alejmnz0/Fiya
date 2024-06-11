package com.app.fiya.team.dto;

import com.app.fiya.validation.annotation.UniqueName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import java.awt.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AddTeam {

    @NotNull(message = "{AddTeam.name.nonempty}")
    @NotEmpty(message = "{AddTeam.name.nonempty}")
    @Length(max = 20, message = "{AddTeam.name.length}")
    @UniqueName
    private String name;

    @NotNull(message = "{AddTeam.url.nonempty}")
    @NotEmpty(message = "{AddTeam.url.nonempty}")
    private String urlImage;

    @NotNull(message = "{AddTeam.color.nonempty}")
    @NotEmpty(message = "{AddTeam.color.nonempty}")
    private String primaryColor;

    private String secondaryColor;
}
