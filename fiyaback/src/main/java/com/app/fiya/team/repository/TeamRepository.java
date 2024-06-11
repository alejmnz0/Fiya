package com.app.fiya.team.repository;

import com.app.fiya.team.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("""
            Select t.name From Team t
            """)
    List<String> getAllName();
}
