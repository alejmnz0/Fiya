package com.app.fiya.user.service;

import com.app.fiya.user.dto.UserRegister;
import com.app.fiya.user.model.User;
import com.app.fiya.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> saveUser (UserRegister data){
        return Optional.of(userRepository.save(User.builder()
                .dni(data.getDni())
                .birthdate(data.getBirthdate())
                .name(data.getName())
                .email(data.getEmail())
                .build()));
    }
}
