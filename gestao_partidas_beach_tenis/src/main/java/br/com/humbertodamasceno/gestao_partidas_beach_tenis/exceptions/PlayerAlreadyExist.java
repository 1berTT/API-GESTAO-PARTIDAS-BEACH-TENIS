package br.com.humbertodamasceno.gestao_partidas_beach_tenis.exceptions;

public class PlayerAlreadyExist extends RuntimeException {
    public PlayerAlreadyExist() {
        super("Player already exists");
    }

}
