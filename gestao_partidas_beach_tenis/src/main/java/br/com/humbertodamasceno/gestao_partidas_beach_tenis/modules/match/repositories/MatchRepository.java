package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.repositories;

import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.entities.MatchEntity;

public interface MatchRepository extends JpaRepository<MatchEntity, UUID> {

    Optional<MatchEntity> findById(UUID id);

}