package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.DTOs.LoadPlayerDTO;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.repositories.PlayerRepository;

@Service
public class LoadUsersUseCase {

    @Autowired
    private PlayerRepository playerRepository;

    public List<LoadPlayerDTO> execute() {
        var result = this.playerRepository.findAll();
        return result.stream().map((player) -> {
            return LoadPlayerDTO.builder().id(player.getId()).name(player.getName()).nickname(player.getNickname())
                    .wins(player.getWins()).losses(player.getLosses()).avatarUrl(player.getAvatarUrl()).build();
        }).collect(Collectors.toList());
    }

}
