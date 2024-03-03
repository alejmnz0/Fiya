package com.app.fiya.field.model;

import com.app.fiya.date.model.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Set;

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
    private String description;

    private double price;
    private int teamCapacity;
    private Ground ground;
    //private Set<Date> dates;
}
