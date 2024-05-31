package com.app.fiya;

import com.app.fiya.date.model.Date;
import com.app.fiya.field.model.Field;
import com.app.fiya.field.model.Ground;
import com.app.fiya.field.repository.FieldRepository;
import com.app.fiya.rent.model.Rent;
import com.app.fiya.team.model.Team;
import com.app.fiya.team.repository.TeamRepository;
import com.app.fiya.user.model.User;
import com.app.fiya.user.model.UserRole;
import com.app.fiya.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitData {
    private final UserRepository userRepository;
    private final FieldRepository fieldRepository;
    private final TeamRepository teamRepository;
    private final PasswordEncoder passwordEncoder;
    Set<Field> fav = new HashSet<>();


    @PostConstruct
    public void InitData() {
        User user = User.builder()
                .name("John Doe")
                .dni("12345678A")
                .image("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png")
                .email("johndoe@gmail.com")
                .favourites(fav)
                .birthdate(LocalDate.of(2004, 6, 11))
                .password(passwordEncoder.encode("123456789"))
                .role(UserRole.ADMIN)
                .build();
        User user2 = User.builder()
                .name("user")
                .dni("87654321A")
                .image("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png")
                .email("user@gmail.com")
                .favourites(fav)
                .birthdate(LocalDate.of(2001, 5, 10))
                .password(passwordEncoder.encode("123456789"))
                .role(UserRole.USER)
                .build();
        userRepository.save(user);
        userRepository.save(user2);

        Team team = Team.builder()
                        .name("Rebaño")
                        .urlImage("asdasdasdas")
                        .primaryColor(Color.BLACK)
                        .secundaryColor(Color.RED)
                        .build();

        teamRepository.save(team);

        fieldRepository.save(Field.builder()
                .name("Los corrales futbol")
                .latitude("123")
                .longitude("321")
                .price(40)
                .teamCapacity(7)
                .ground(Ground.CLAY)
                //.dates(Set.of(Date.builder().isFree(true).date(LocalDateTime.of(2024, 3, 5, 12, 0)).captain(User.builder().name("Andres").dni("12345678Z").email("andresito@gmail.com").build()).build()))
                .build());

        Rent r = Rent.builder()
                .field(fieldRepository.findById(1L).get())
                .rentTime(LocalDateTime.of(2024, 4, 12, 12, 0))
                .build();

        //fieldRepository.findById(1L).get().setRenting(Set.of(r));

        fieldRepository.save(Field.builder()
                .name("Los mares futbol")
                .latitude("3123")
                .longitude("3213")
                .price(40)
                .teamCapacity(7)
                .ground(Ground.CONCRETE)
                //.dates(Set.of(Date.builder().isFree(true).date(LocalDateTime.of(2024, 3, 5, 12, 0)).captain(User.builder().name("Andres").dni("12345678Z").email("andresito@gmail.com").build()).build()))
                .build());

        fieldRepository.save(Field.builder()
                .name("Campo de ejemplo")
                .latitude("3123")
                .longitude("3213")
                .price(40)
                .teamCapacity(7)
                .ground(Ground.NATURAL)
                //.dates(Set.of(Date.builder().isFree(true).date(LocalDateTime.of(2024, 3, 5, 12, 0)).captain(User.builder().name("Andres").dni("12345678Z").email("andresito@gmail.com").build()).build()))
                .build());


        fieldRepository.save(Field.builder()
                .name("Campo de futbol bonito")
                .latitude("3123")
                .longitude("3213")
                .price(40)
                .teamCapacity(7)
                .ground(Ground.ARTIFICIAL)
                //.dates(Set.of(Date.builder().isFree(true).date(LocalDateTime.of(2024, 3, 5, 12, 0)).captain(User.builder().name("Andres").dni("12345678Z").email("andresito@gmail.com").build()).build()))
                .build());

        fieldRepository.save(Field.builder()
                .name("SuperFutbol camp")
                .latitude("3123")
                .longitude("3213")
                .price(30)
                .teamCapacity(5)
                .ground(Ground.ARTIFICIAL)
                //.dates(Set.of(Date.builder().isFree(true).date(LocalDateTime.of(2024, 3, 5, 12, 0)).captain(User.builder().name("Andres").dni("12345678Z").email("andresito@gmail.com").build()).build()))
                .build());

        fieldRepository.save(Field.builder()
                .name("Campete futbol")
                .latitude("3123")
                .longitude("3213")
                .price(42)
                .teamCapacity(7)
                .ground(Ground.CONCRETE)
                //.dates(Set.of(Date.builder().isFree(true).date(LocalDateTime.of(2024, 3, 5, 12, 0)).captain(User.builder().name("Andres").dni("12345678Z").email("andresito@gmail.com").build()).build()))
                .build());

        fieldRepository.save(Field.builder()
                .name("Futbol a montones")
                .latitude("3123")
                .longitude("3213")
                .price(30)
                .teamCapacity(7)
                .ground(Ground.CLAY)
                //.dates(Set.of(Date.builder().isFree(true).date(LocalDateTime.of(2024, 3, 5, 12, 0)).captain(User.builder().name("Andres").dni("12345678Z").email("andresito@gmail.com").build()).build()))
                .build());

        fieldRepository.save(Field.builder()
                .name("Campo de futbol de triana")
                .latitude("3123")
                .longitude("3213")
                .price(40)
                .teamCapacity(7)
                .ground(Ground.PARQUET)
                //.dates(Set.of(Date.builder().isFree(true).date(LocalDateTime.of(2024, 3, 5, 12, 0)).captain(User.builder().name("Andres").dni("12345678Z").email("andresito@gmail.com").build()).build()))
                .build());


        fieldRepository.save(Field.builder()
                .name("Futbol San Jose camp")
                .latitude("3123")
                .longitude("3213")
                .price(50)
                .teamCapacity(11)
                .ground(Ground.NATURAL)
                //.dates(Set.of(Date.builder().isFree(true).date(LocalDateTime.of(2024, 3, 5, 12, 0)).captain(User.builder().name("Andres").dni("12345678Z").email("andresito@gmail.com").build()).build()))
                .build());


        fieldRepository.save(Field.builder()
                .name("Campo Sevilla centro")
                .latitude("3123")
                .longitude("3213")
                .price(45)
                .teamCapacity(11)
                .ground(Ground.CONCRETE)
                //.dates(Set.of(Date.builder().isFree(true).date(LocalDateTime.of(2024, 3, 5, 12, 0)).captain(User.builder().name("Andres").dni("12345678Z").email("andresito@gmail.com").build()).build()))
                .build());


        fieldRepository.save(Field.builder()
                .name("Campo futbol sevilla camp")
                .latitude("3123")
                .longitude("3213")
                .price(34)
                .teamCapacity(7)
                .ground(Ground.PARQUET)
                //.dates(Set.of(Date.builder().isFree(true).date(LocalDateTime.of(2024, 3, 5, 12, 0)).captain(User.builder().name("Andres").dni("12345678Z").email("andresito@gmail.com").build()).build()))
                .build());


    }
}
