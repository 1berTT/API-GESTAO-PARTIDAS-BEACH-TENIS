package br.com.humbertodamasceno.gestao_partidas_beach_tenis.exceptions;

public class MatchAlreadyExist extends RuntimeException {
    public MatchAlreadyExist() {
        super("Match already exists");
    }
}
