package com.app.fiya.field.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Field {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String latitude;
    private String longitude;
}
