package com.app.fiya.field.service;

import com.app.fiya.MyPage;
import com.app.fiya.date.model.Date;
import com.app.fiya.exception.NotFoundException;
import com.app.fiya.field.dto.AddField;
import com.app.fiya.field.dto.FieldDetailResponse;
import com.app.fiya.field.dto.FieldListResponse;
import com.app.fiya.field.model.Field;
import com.app.fiya.field.repository.FieldRepository;
import com.app.fiya.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FieldService {
    private final FieldRepository fieldRepository;

    public Optional<Field> save (AddField data) {
        return Optional.of(fieldRepository.save(Field.builder()
                .name(data.getName())
                .latitude(data.getLatitude())
                .longitude(data.getLongitude())
                //.dates(Set.of(Date.builder().isFree(true).date(LocalDateTime.of(2024, 3, 5, 12, 0)).captain(User.builder().name("Andres").dni("12345678Z").email("andresito@gmail.com").build()).build()))
                .build()));
    }

    public MyPage<FieldListResponse> getAll (Pageable pageable) {
        Page<Field> result = fieldRepository.findAll(pageable);
        if (result.isEmpty())
            throw new NotFoundException("Field");
        Page<FieldListResponse> response = result.map(FieldListResponse::of);
        return MyPage.of(response);
    }

    public MyPage<FieldListResponse> getFavFieldsByUser (Pageable pageable, User user) {
        Page<Field> result = fieldRepository.getFavFieldsByUser(pageable, user.getFavourites());
        if (result.isEmpty())
            throw new NotFoundException("Field");
        Page<FieldListResponse> response = result.map(FieldListResponse::of);
        return MyPage.of(response);
    }

    public FieldDetailResponse getFieldById (Long id) {
        Optional<Field> data = fieldRepository.findById(id);
        if (data.isEmpty())
            throw new NotFoundException("Field");

        return FieldDetailResponse.of(data.get());
    }
}
