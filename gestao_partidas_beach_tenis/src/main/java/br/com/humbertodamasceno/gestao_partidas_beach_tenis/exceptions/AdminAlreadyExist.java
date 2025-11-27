package br.com.humbertodamasceno.gestao_partidas_beach_tenis.exceptions;

public class AdminAlreadyExist extends RuntimeException {
    public AdminAlreadyExist() {
        super("Admin already exists");
    }

}
