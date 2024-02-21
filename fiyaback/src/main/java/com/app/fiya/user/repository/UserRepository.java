package com.app.fiya.user.repository;

import com.app.fiya.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findFirstByDni (String dni);

    @Query("""
            Select u.dni From User u
            """)
    List<String> getAlldni();

    @Query("""
            Select u.email From User u
            """)
    List<String> getAllemail();
}
