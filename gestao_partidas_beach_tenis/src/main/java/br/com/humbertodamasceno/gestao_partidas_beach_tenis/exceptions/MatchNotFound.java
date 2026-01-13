package br.com.humbertodamasceno.gestao_partidas_beach_tenis.exceptions;

public class MatchNotFound extends RuntimeException {
    public MatchNotFound() {
        super("Match not found");
    }
}
