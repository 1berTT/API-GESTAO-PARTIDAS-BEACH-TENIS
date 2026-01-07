package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.useCases.CreateMatchUseCase;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.useCases.DeleteMatchUseCase;
import jakarta.validation.Valid;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.entities.MatchEntity;
import java.util.UUID;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.useCases.ListMatchesUseCase;
import java.time.LocalDate;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private CreateMatchUseCase createMatchUseCase;

    @Autowired
    private DeleteMatchUseCase deleteMatchUseCase;

    @Autowired
    private ListMatchesUseCase listMatchesUseCase;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody MatchEntity matchEntity) {

        try {
            var result = this.createMatchUseCase.execute(matchEntity);
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        try {
            this.deleteMatchUseCase.execute(id);
            return ResponseEntity.ok().body("Match deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllMatches(
            @PageableDefault(page = 0, size = 10) Pageable pageable,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        try {
            var result = this.listMatchesUseCase.execute(pageable, date);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
