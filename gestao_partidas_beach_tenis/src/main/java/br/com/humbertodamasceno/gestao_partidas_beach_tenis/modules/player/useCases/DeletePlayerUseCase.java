package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.useCases;

import java.io.IOException;
import java.util.UUID;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.repositories.PlayerRepository;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.exceptions.PlayerNotFound;

@Service
public class DeletePlayerUseCase {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public void execute(UUID id) throws IOException {

        var player = this.playerRepository.findById(id).orElseThrow(() -> {
            throw new PlayerNotFound();
        });

        // Deletar a imagem do avatar se existir
        if (player.getAvatarUrl() != null && !player.getAvatarUrl().isEmpty()) {
            fileStorageService.deleteFile(player.getAvatarUrl());
        }

        this.playerRepository.delete(player);

    }

}
