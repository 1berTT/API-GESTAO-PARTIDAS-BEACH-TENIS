package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.useCases;

import java.util.UUID;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.exceptions.PlayerNotFound;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.DTOs.LoadPlayerDTO;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadPlayerUseCase {

    @Autowired
    private PlayerRepository playerRepository;

    public LoadPlayerDTO execute(UUID id) {

        var result = this.playerRepository.findById(id).orElseThrow(() -> {
            throw new PlayerNotFound();
        });

        var playerDTO = LoadPlayerDTO.builder().id(result.getId()).name(result.getName())
                .nickname(result.getNickname()).wins(result.getWins()).losses(result.getLosses())
                .avatarUrl(result.getAvatarUrl()).build();

        return playerDTO;

    }
}
