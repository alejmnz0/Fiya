package com.app.fiya.team.service;

import com.app.fiya.MyPage;
import com.app.fiya.exception.NotFoundException;
import com.app.fiya.team.dto.AddPlayer;
import com.app.fiya.team.dto.AddTeam;
import com.app.fiya.team.dto.TeamListResponse;
import com.app.fiya.team.model.Team;
import com.app.fiya.team.repository.TeamRepository;
import com.app.fiya.user.model.User;
import com.app.fiya.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public MyPage<TeamListResponse> getAll (Pageable pageable){
        Page<Team> result = teamRepository.findAll(pageable);
        if (result.isEmpty())
            throw new NotFoundException("User");
        Page<TeamListResponse> respuesta = result.map(TeamListResponse::of);
        return MyPage.of(respuesta);
    }

    public Optional<Team> saveTeam (AddTeam data, User captain){
        Set<User> players = new HashSet<>();
        players.add(captain);

        String hex = data.getPrimaryColor();
        String hex2 = data.getSecondaryColor();
        if (hex.startsWith("#"))
            hex = hex.substring(1);
        if (hex2.startsWith("#"))
            hex2 = hex2.substring(1);
        int intValue = Integer.parseInt(hex, 16);
        int intValue2 = Integer.parseInt(hex2, 16);

        Team team = Team.builder()
                .name(data.getName())
                .urlImage(data.getUrlImage())
                .captain(captain)
                .primaryColor(new Color(intValue))
                .secundaryColor(new Color(intValue2))
                .players(players)
                .build();

        return Optional.of(teamRepository.save(team));
    }

    public boolean existName(String name ) {
        return teamRepository.getAllName().stream().toList().contains(name);
    }

    public Optional<Team> addTeam (AddTeam data){
        Set<User> players = new HashSet<>();

        String hex = data.getPrimaryColor();
        String hex2 = data.getSecondaryColor();
        if (hex.startsWith("#"))
            hex = hex.substring(1);
        if (hex2.startsWith("#"))
            hex2 = hex2.substring(1);
        int intValue = Integer.parseInt(hex, 16);
        int intValue2 = Integer.parseInt(hex2, 16);

        Team team = Team.builder()
                .name(data.getName())
                .urlImage(data.getUrlImage())
                .primaryColor(new Color(intValue))
                .secundaryColor(new Color(intValue2))
                .players(players)
                .build();

        return Optional.of(teamRepository.save(team));
    }

    @Transactional
    public void addPlayer(AddPlayer data) {
        Optional<Team> teamOp = teamRepository.findById(data.getTeamId());
        Optional<User> playerOp = userRepository.findById(data.getPlayerId());
        if (teamOp.isPresent() && playerOp.isPresent()) {
            Team team = teamOp.get();
            User player = playerOp.get();

            // Establecer la relación bidireccional
            player.setTeam(team);
            team.getPlayers().add(player);

            // Guardar el jugador y el equipo
            userRepository.save(player);
            teamRepository.save(team);
        }
    }
}
