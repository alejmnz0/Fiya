package com.app.fiya.rent.model;

import com.app.fiya.field.model.Field;
import com.app.fiya.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Field field;

    private LocalDateTime rentTime;

    @ManyToOne
    private User user;
}
