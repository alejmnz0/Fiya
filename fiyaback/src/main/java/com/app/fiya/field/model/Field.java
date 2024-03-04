package com.app.fiya.field.model;

import com.app.fiya.rent.model.Rent;
import jakarta.persistence.*;
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

    //@OneToMany(mappedBy = "field", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //private Set<Rent> renting;
}
