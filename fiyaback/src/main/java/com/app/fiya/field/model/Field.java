package com.app.fiya.field.model;

import com.app.fiya.appointment.model.Appointment;
import com.app.fiya.rent.model.Rent;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
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

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Appointment> appointments = new ArrayList<>();

    //@OneToMany(mappedBy = "field", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //private Set<Rent> renting;
}
