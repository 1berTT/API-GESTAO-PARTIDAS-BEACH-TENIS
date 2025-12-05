package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @PostMapping("/create")
    public void create(@RequestBody String name) {
        System.out.println(name);

    }

}
