package com.app.fiya.team.dto;

import com.app.fiya.field.model.Ground;
import com.app.fiya.user.model.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AddTeam {

    @NotNull(message = "{AddTeam.name.nonempty}")
    @NotEmpty(message = "{AddTeam.name.nonempty}")
    @Length(max = 20, message = "{AddTeam.name.length}")
    private String name;

    @NotNull(message = "{AddTeam.url.nonempty}")
    @NotEmpty(message = "{AddTeam.url.nonempty}")
    private String urlImage;
}
