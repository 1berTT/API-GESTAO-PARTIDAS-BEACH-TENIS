package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.admin.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.admin.entities.AdminEntity;

public interface AdminRepositoty extends JpaRepository<AdminEntity, UUID> {
    Optional<AdminEntity> findByUsername(String username);
}
