package com.app.fiya.field.service;

import com.app.fiya.field.dto.AddField;
import com.app.fiya.field.model.Field;
import com.app.fiya.field.repository.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FieldService {
    private final FieldRepository fieldRepository;

    public Optional<Field> save (AddField data) {
        return Optional.of(fieldRepository.save(Field.builder()
                .name(data.getName())
                .latitude(data.getLatitude())
                .longitude(data.getLongitude())
                .build()));
    }
}
