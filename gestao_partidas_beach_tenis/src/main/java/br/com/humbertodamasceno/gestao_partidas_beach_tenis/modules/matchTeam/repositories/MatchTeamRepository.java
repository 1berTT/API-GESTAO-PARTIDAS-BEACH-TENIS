package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.matchTeam.repositories;

import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.matchTeam.entities.MatchTeamEntity;

public interface MatchTeamRepository extends JpaRepository<MatchTeamEntity, UUID> {

    Optional<MatchTeamEntity> findById(UUID id);

    Optional<MatchTeamEntity> findByMatch_IdAndTeamNumber(UUID matchId, Integer teamNumber);

}
