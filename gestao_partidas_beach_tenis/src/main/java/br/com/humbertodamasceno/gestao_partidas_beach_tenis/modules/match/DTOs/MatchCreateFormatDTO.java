package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.DTOs;

import java.util.UUID;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.entities.MatchEntity;
import lombok.Data;

@Data
public class MatchCreateFormatDTO {

    private MatchEntity match;

    private UUID idPlayer1;

    private UUID idPlayer2;

    private UUID idPlayer3;

    private UUID idPlayer4;

}
