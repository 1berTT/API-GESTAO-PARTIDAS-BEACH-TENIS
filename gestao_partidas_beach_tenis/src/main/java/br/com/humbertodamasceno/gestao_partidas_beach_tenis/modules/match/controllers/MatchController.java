package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.useCases.CreateMatchUseCase;
import jakarta.validation.Valid;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.entities.MatchEntity;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private CreateMatchUseCase createMatchUseCase;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody MatchEntity matchEntity) {

        try {
            var result = this.createMatchUseCase.execute(matchEntity);
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

}
