package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.entities.PlayerEntity;

public interface PlayerRepository extends JpaRepository<PlayerEntity, UUID> {
    Optional<PlayerEntity> findByNickname(String nickname);

    Optional<PlayerEntity> findById(UUID id);
}
