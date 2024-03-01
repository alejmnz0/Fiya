package com.app.fiya.date.model;

import com.app.fiya.user.model.User;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Date {

    private LocalDateTime date;
    private boolean isFree;

    //User who rents the field
    private User captain;
}
