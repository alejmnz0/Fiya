package com.app.fiya.field.service;

import com.app.fiya.MyPage;
import com.app.fiya.date.model.Date;
import com.app.fiya.exception.NotFoundException;
import com.app.fiya.field.dto.AddField;
import com.app.fiya.field.dto.EditField;
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
import java.util.stream.Collectors;

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
                .ground(data.getGround())
                .price(data.getPrice())
                .teamCapacity(data.getTeamCapacity())
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
        Page<Field> result = fieldRepository.getFavFieldsByUser(pageable, user.getFavourites().stream().map(Field::getId).collect(Collectors.toSet()));
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

    public void delete (Long id){
        Optional<Field> aborrar = fieldRepository.findById(id);

        if (aborrar.isPresent())
            fieldRepository.deleteById(id);
        else
            throw new NotFoundException("Field");
    }

    public Optional<Field> edit (Long id, EditField newField){
        Optional<Field> acambiar = fieldRepository.findById(id);

        if (acambiar.isPresent()){
            if (newField.getName() != null)
                acambiar.get().setName(newField.getName());
            if (newField.getPrice() != 0)
                acambiar.get().setPrice(newField.getPrice());
            if (newField.getTeamCapacity() != 0)
                acambiar.get().setTeamCapacity(newField.getTeamCapacity());
            if (newField.getLatitude() != null)
                acambiar.get().setLatitude(newField.getLatitude());
            if (newField.getLongitude() != null)
                acambiar.get().setLongitude(newField.getLongitude());
            if (newField.getGround() != null)
                acambiar.get().setGround(newField.getGround());
            if (newField.getDescription() != null)
                acambiar.get().setDescription(newField.getDescription());
            return Optional.of(fieldRepository.save(acambiar.get()));
        }

        throw new NotFoundException("Profesor");
    }
}
