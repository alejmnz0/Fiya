package com.app.fiya.field.repository;

import com.app.fiya.field.model.Field;
import com.app.fiya.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface FieldRepository extends JpaRepository<Field, Long> {

    @Query("""
            Select f From Field f where f.id IN :favouriteIds
            """)
    Page<Field> getFavFieldsByUser(Pageable pageable, @Param("favouriteIds")Set<Long> favourites);
}
