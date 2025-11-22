package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.admin.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.admin.entities.AdminEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/create")
    public void createAdmin(@Valid @RequestBody AdminEntity adminEntity) {
        System.out.println(adminEntity);
    }

}
