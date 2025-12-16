package br.com.humbertodamasceno.gestao_partidas_beach_tenis.exceptions;

public class PlayerNotFound extends RuntimeException {
    public PlayerNotFound() {
        super("Player not found");
    }
}
