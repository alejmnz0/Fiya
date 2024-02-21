package com.app.fiya.user.service;

import com.app.fiya.user.dto.UserRegister;
import com.app.fiya.user.model.User;
import com.app.fiya.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findById(UUID userId) {return userRepository.findById(userId);}

    public Optional<User> findByDni (String dni) {return userRepository.findFirstByDni(dni);}

    public Optional<User> saveUser (UserRegister data){
        return Optional.of(userRepository.save(User.builder()
                .dni(data.getDni())
                .birthdate(data.getBirthdate())
                .password(passwordEncoder.encode(data.getPassword()))
                .name(data.getName())
                .email(data.getEmail())
                .build()));
    }
}
