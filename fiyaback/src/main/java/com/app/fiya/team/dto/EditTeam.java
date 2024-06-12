package com.app.fiya.team.dto;

import com.app.fiya.validation.annotation.ExeptionUniqueName;
import com.fasterxml.jackson.annotation.JsonView;
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
@ExeptionUniqueName(
        actualNameField = "actualName", newNameField = "name"
)
public class EditTeam {

    @NotNull(message = "{AddTeam.name.nonempty}")
    @NotEmpty(message = "{AddTeam.name.nonempty}")
    @Length(max = 20, message = "{AddTeam.name.length}")
    @JsonView(EditTeam.EditTeamResponse.class)
    private String name;

    private String actualName;

    @NotNull(message = "{AddTeam.url.nonempty}")
    @NotEmpty(message = "{AddTeam.url.nonempty}")
    private String urlImage;

    @NotNull(message = "{AddTeam.color.nonempty}")
    @NotEmpty(message = "{AddTeam.color.nonempty}")
    @JsonView(EditTeam.EditTeamResponse.class)
    private String primaryColor;

    @JsonView(EditTeam.EditTeamResponse.class)
    private String secondaryColor;

    public static class EditTeamResponse {}
}
