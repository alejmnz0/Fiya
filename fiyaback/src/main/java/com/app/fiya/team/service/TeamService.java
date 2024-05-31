package com.app.fiya.team.service;

import com.app.fiya.MyPage;
import com.app.fiya.exception.NotFoundException;
import com.app.fiya.team.dto.AddTeam;
import com.app.fiya.team.dto.TeamListResponse;
import com.app.fiya.team.model.Team;
import com.app.fiya.team.repository.TeamRepository;
import com.app.fiya.user.dto.UserListResponse;
import com.app.fiya.user.model.User;
import com.app.fiya.user.repository.UserRepository;
import com.app.fiya.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

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

        Team team = Team.builder()
                .name(data.getName())
                .urlImage(data.getUrlImage())
                .captain(captain)
                .primaryColor(data.getPrimaryColor())
                .secundaryColor(data.getSecundaryColor())
                .players(players)
                .build();

        return Optional.of(teamRepository.save(team));
    }
}
