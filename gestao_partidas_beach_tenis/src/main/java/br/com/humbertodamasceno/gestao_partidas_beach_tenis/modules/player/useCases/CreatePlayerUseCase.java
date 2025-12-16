package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.useCases;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.entities.PlayerEntity;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.repositories.PlayerRepository;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.exceptions.PlayerAlreadyExist;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.services.FileStorageService;

@Service
public class CreatePlayerUseCase {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public PlayerEntity execute(PlayerEntity playerEntity, MultipartFile avatarFile) throws IOException {
        this.playerRepository.findByNickname(playerEntity.getNickname()).ifPresent((player) -> {
            throw new PlayerAlreadyExist();
        });

        // Salvar avatar se fornecido
        if (avatarFile != null && !avatarFile.isEmpty()) {
            String avatarUrl = fileStorageService.storeFile(avatarFile);
            playerEntity.setAvatarUrl(avatarUrl);
        }

        return this.playerRepository.save(playerEntity);
    }

}
