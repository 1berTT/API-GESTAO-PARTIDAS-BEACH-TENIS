package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.controllers;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.entities.PlayerEntity;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.useCases.CreatePlayerUseCase;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.useCases.LoadPlayerUseCase;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.useCases.LoadUsersUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private CreatePlayerUseCase createPlayerUseCase;

    @Autowired
    private LoadPlayerUseCase loadPlayerUseCase;

    @Autowired
    private LoadUsersUseCase loadUsersUseCase;

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
            var result = this.loadUsersUseCase.execute();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
