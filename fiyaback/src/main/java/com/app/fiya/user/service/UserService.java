package com.app.fiya.user.service;

import com.app.fiya.MyPage;
import com.app.fiya.exception.NotFoundException;
import com.app.fiya.field.model.Field;
import com.app.fiya.field.repository.FieldRepository;
import com.app.fiya.user.dto.UserEdit;
import com.app.fiya.user.dto.UserListResponse;
import com.app.fiya.user.dto.UserRegister;
import com.app.fiya.user.model.User;
import com.app.fiya.user.model.UserRole;
import com.app.fiya.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final FieldRepository fieldRepository;
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
                .role(UserRole.USER)
                .isOnTeam(false)
                .build()));
    }

    public boolean existDni(String dni ) {
        return userRepository.getAlldni().stream().toList().contains(dni);
    }

    public boolean existEmail(String email ) {
        return userRepository.getAllemail().stream().toList().contains(email);
    }


    public boolean favourite (Long fieldId, User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());

        if (optionalUser.isPresent()){
            User existingUSer = optionalUser.get();
            Set<Field> favourites = new HashSet<>();
            if (existingUSer.getFavourites() != null){
                favourites = existingUSer.getFavourites();
            }

            if (favourites.contains(fieldRepository.findById(fieldId).orElseThrow())){
                favourites.remove(fieldRepository.findById(fieldId).orElseThrow());
            } else {
                favourites.add(fieldRepository.findById(fieldId).orElseThrow());
            }
            existingUSer.setFavourites(favourites);
            userRepository.save(existingUSer);
            return true;
        }
        return false;
    }

    public Optional<User> editUser (UserEdit data, User user) {
        String name = (data.getName() != null && !data.getName().isEmpty()) ? data.getName() : user.getName();
        LocalDate birthdate = (data.getBirthdate() != null ? data.getBirthdate() : user.getBirthdate());
        String email = (data.getEmail() != null && !data.getEmail().isEmpty()) ? data.getEmail() : user.getEmail();
        boolean isOnTeam = data.isOnTeam();

        Optional<User> newUser = userRepository.findById(user.getId());
        if (newUser.isPresent()){
            newUser.get().setName(name);
            newUser.get().setBirthdate(birthdate);
            newUser.get().setEmail(email);
            newUser.get().setOnTeam(isOnTeam);
            return Optional.of(userRepository.save(newUser.get()));
        }
        return Optional.empty();
    }

    public Optional<User> editUserById (UserEdit data, UUID id) {

        Optional<User> aCambiar = userRepository.findById(id);
        if (aCambiar.isPresent()){
            if (data.getName() != null)
                aCambiar.get().setName(data.getName());
            if (data.getEmail() != null)
                aCambiar.get().setEmail(data.getEmail());
            if (data.getBirthdate() != null)
                aCambiar.get().setBirthdate(data.getBirthdate());
            return Optional.of(userRepository.save(aCambiar.get()));
        }
        throw new NotFoundException("User");
    }

    public MyPage<UserListResponse> getAll(Pageable pageable) {
        Page<User> result = userRepository.findAll(pageable);
        if (result.isEmpty())
            throw new NotFoundException("User");
        Page<UserListResponse> respuesta = result.map(UserListResponse::of);
        return MyPage.of(respuesta);
    }

    public void delete (UUID id){
        Optional<User> aborrar = userRepository.findById(id);

        if (aborrar.isPresent())
            userRepository.deleteById(id);
        else
            throw new NotFoundException("User");
    }
}
