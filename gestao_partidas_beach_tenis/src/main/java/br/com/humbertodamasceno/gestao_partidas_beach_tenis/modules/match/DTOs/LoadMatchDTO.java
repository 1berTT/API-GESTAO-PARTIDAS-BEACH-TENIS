package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.DTOs;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.DTOs.LoadPlayerDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoadMatchDTO {
    private UUID id;
    private LocalDateTime matchDate;
    private Integer team1Score;
    private Integer team2Score;
    private Integer winnerTeam;
    private List<LoadPlayerDTO> winnerTeamPlayers;
    private List<LoadPlayerDTO> loserTeamPlayers;

}
