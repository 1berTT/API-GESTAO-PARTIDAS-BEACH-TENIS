package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.controllers;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.entities.PlayerEntity;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.useCases.CreatePlayerUseCase;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.useCases.DeletePlayerUseCase;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.useCases.LoadPlayerUseCase;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.useCases.LoadPlayersUseCase;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.useCases.UpdatePlayerUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private CreatePlayerUseCase createPlayerUseCase;

    @Autowired
    private LoadPlayerUseCase loadPlayerUseCase;

    @Autowired
    private LoadPlayersUseCase loadPlayersUseCase;

    @Autowired
    private DeletePlayerUseCase deletePlayerUseCase;

    @Autowired
    private UpdatePlayerUseCase updatePlayerUseCase;

    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<Object> create(
            @Valid @ModelAttribute PlayerEntity playerEntity,
            @RequestPart(value = "avatar", required = false) MultipartFile avatarFile) {
        try {
            var result = this.createPlayerUseCase.execute(playerEntity, avatarFile);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao salvar o arquivo: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable UUID id) {
        try {
            var result = this.loadPlayerUseCase.execute(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAll() {
        try {
            var result = this.loadPlayersUseCase.execute();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        try {
            this.deletePlayerUseCase.execute(id);
            return ResponseEntity.ok().body("Player deleted successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar o arquivo: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping(value = "/update", consumes = "multipart/form-data")
    public ResponseEntity<Object> update(
            @Valid @ModelAttribute PlayerEntity playerEntity,
            @RequestPart(value = "avatar", required = false) MultipartFile avatarFile) {

        try {
            var result = this.updatePlayerUseCase.execute(playerEntity, avatarFile);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao salvar o arquivo: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
