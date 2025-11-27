package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.admin.DTOs.AdminLoginRequestDTO;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.admin.entities.AdminEntity;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.admin.useCases.CreateAdminUseCase;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.admin.useCases.LoginAdminUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CreateAdminUseCase createAdminUseCase;

    @Autowired
    private LoginAdminUseCase loginAdminUseCase;

    @PostMapping("/create")
    public ResponseEntity<Object> createAdmin(@Valid @RequestBody AdminEntity admin) {
        try {
            var result = this.createAdminUseCase.execute(admin);
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AdminLoginRequestDTO adminLoginRequestDTO) {
        try {

            var tokenResponse = this.loginAdminUseCase.execute(adminLoginRequestDTO);

            return ResponseEntity.ok().body(tokenResponse);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
