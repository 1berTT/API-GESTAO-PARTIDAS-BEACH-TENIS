package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.entities.PlayerEntity;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.repositories.PlayerRepository;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.services.FileStorageService;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.exceptions.PlayerNotFound;
import java.io.IOException;

@Service
public class UpdatePlayerUseCase {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public PlayerEntity execute(PlayerEntity playerEntity, MultipartFile avatarFile) throws IOException {
        var updatedPlayer = this.playerRepository.findById(playerEntity.getId()).orElseThrow(() -> {
            throw new PlayerNotFound();
        });

        updatedPlayer.setName(playerEntity.getName());
        updatedPlayer.setNickname(playerEntity.getNickname());

        if (avatarFile != null && !avatarFile.isEmpty()) {
            // Salvar o avatarUrl antigo antes de atualizar
            String oldAvatarUrl = updatedPlayer.getAvatarUrl();

            // Salvar o novo avatar
            String avatarUrl = fileStorageService.storeFile(avatarFile);
            updatedPlayer.setAvatarUrl(avatarUrl);

            // Deletar a imagem antiga se existir
            if (oldAvatarUrl != null && !oldAvatarUrl.isEmpty()) {
                fileStorageService.deleteFile(oldAvatarUrl);
            }
        }

        return this.playerRepository.save(updatedPlayer);

    }

}
