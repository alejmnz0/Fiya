package com.app.fiya.team.service;

import com.app.fiya.team.dto.AddTeam;
import com.app.fiya.team.model.Team;
import com.app.fiya.team.repository.TeamRepository;
import com.app.fiya.user.model.User;
import com.app.fiya.user.repository.UserRepository;
import com.app.fiya.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public Optional<Team> saveTeam (AddTeam data, User captain){
        Set<User> players = new HashSet<>();
        players.add(captain);

        Team team = Team.builder()
                .name(data.getName())
                .urlImage(data.getUrlImage())
                .captain(captain)
                .players(players)
                .build();

        return Optional.of(teamRepository.save(team));
    }
}
