package com.app.fiya;

import com.app.fiya.user.model.User;
import com.app.fiya.user.model.UserRole;
import com.app.fiya.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class InitData {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void InitDat() {
        User user = User.builder()
                .name("Alejandro")
                .dni("29516575P")
                .email("jimenezalejandro505@gmail.com")
                .birthdate(LocalDate.of(2004, 6, 11))
                .password(passwordEncoder.encode("123456789"))
                .role(UserRole.USER)
                .build();
        userRepository.save(user);
    }
}
