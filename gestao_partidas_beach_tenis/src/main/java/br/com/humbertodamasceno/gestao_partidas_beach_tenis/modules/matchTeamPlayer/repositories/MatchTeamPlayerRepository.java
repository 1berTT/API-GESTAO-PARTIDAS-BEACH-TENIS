package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.matchTeamPlayer.repositories;

import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.matchTeamPlayer.entities.MatchTeamPlayerEntity;

public interface MatchTeamPlayerRepository extends JpaRepository<MatchTeamPlayerEntity, UUID> {

    Optional<MatchTeamPlayerEntity> findById(UUID id);

    Optional<MatchTeamPlayerEntity> findByPlayer_Id(UUID playerId);

    Optional<MatchTeamPlayerEntity> findByMatchTeam_Id(UUID matchTeamId);

}
