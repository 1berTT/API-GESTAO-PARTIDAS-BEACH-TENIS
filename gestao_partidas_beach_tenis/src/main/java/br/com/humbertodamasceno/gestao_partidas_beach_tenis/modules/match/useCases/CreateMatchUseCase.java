package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.entities.MatchEntity;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.repositories.MatchRepository;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.DTOs.MatchCreateFormatDTO;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.repositories.PlayerRepository;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.exceptions.PlayerNotFound;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.matchTeamPlayer.repositories.MatchTeamPlayerRepository;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.matchTeam.repositories.MatchTeamRepository;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.matchTeam.entities.MatchTeamEntity;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.matchTeamPlayer.entities.MatchTeamPlayerEntity;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.DTOs.LoadMatchDTO;
import java.util.List;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.DTOs.LoadPlayerDTO;

@Service
public class CreateMatchUseCase {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private MatchTeamPlayerRepository matchTeamPlayerRepository;

    @Autowired
    private MatchTeamRepository matchTeamRepository;

    public LoadMatchDTO execute(MatchCreateFormatDTO matchCreateFormatDTO) {

        var match = matchCreateFormatDTO.getMatch();

        // Salvar o match primeiro para que tenha um ID persistido
        match = this.matchRepository.save(match);

        var team1Player1 = this.playerRepository.findById(matchCreateFormatDTO.getIdPlayer1()).orElseThrow(() -> {
            throw new PlayerNotFound();
        });
        var team1Player2 = this.playerRepository.findById(matchCreateFormatDTO.getIdPlayer2()).orElseThrow(() -> {
            throw new PlayerNotFound();
        });
        var team2Player1 = this.playerRepository.findById(matchCreateFormatDTO.getIdPlayer3()).orElseThrow(() -> {
            throw new PlayerNotFound();
        });
        var team2Player2 = this.playerRepository.findById(matchCreateFormatDTO.getIdPlayer4()).orElseThrow(() -> {
            throw new PlayerNotFound();
        });

        var team1 = this.matchTeamRepository.save(MatchTeamEntity.builder()
                .match(match)
                .teamNumber(1)
                .build());

        var team2 = this.matchTeamRepository.save(MatchTeamEntity.builder()
                .match(match)
                .teamNumber(2)
                .build());

        this.matchTeamPlayerRepository.save(MatchTeamPlayerEntity.builder()
                .matchTeam(team1)
                .player(team1Player1)
                .build());
        this.matchTeamPlayerRepository.save(MatchTeamPlayerEntity.builder()
                .matchTeam(team1)
                .player(team1Player2)
                .build());

        this.matchTeamPlayerRepository.save(MatchTeamPlayerEntity.builder()
                .matchTeam(team2)
                .player(team2Player1)
                .build());
        this.matchTeamPlayerRepository.save(MatchTeamPlayerEntity.builder()
                .matchTeam(team2)
                .player(team2Player2)
                .build());

        LoadMatchDTO resultMatchDTO = null;

        if (match.getWinnerTeam() == 1) {
            team1Player1.setWins(team1Player1.getWins() + 1);
            team1Player2.setWins(team1Player2.getWins() + 1);
            team2Player1.setLosses(team2Player1.getLosses() + 1);
            team2Player2.setLosses(team2Player2.getLosses() + 1);

            resultMatchDTO = LoadMatchDTO.builder()
                    .id(match.getId())
                    .matchDate(match.getMatchDate())
                    .team1Score(match.getTeam1Score())
                    .team2Score(match.getTeam2Score())
                    .winnerTeam(match.getWinnerTeam())
                    .winnerTeamPlayers(List.of(
                            LoadPlayerDTO.builder().id(team1Player1.getId()).name(team1Player1.getName())
                                    .nickname(team1Player1.getNickname()).wins(team1Player1.getWins())
                                    .losses(team1Player1.getLosses()).avatarUrl(team1Player1.getAvatarUrl()).build(),
                            LoadPlayerDTO.builder().id(team1Player2.getId()).name(team1Player2.getName())
                                    .nickname(team1Player2.getNickname()).wins(team1Player2.getWins())
                                    .losses(team1Player2.getLosses()).avatarUrl(team1Player2.getAvatarUrl()).build()))
                    .loserTeamPlayers(List.of(
                            LoadPlayerDTO.builder().id(team2Player1.getId()).name(team2Player1.getName())
                                    .nickname(team2Player1.getNickname()).wins(team2Player1.getWins())
                                    .losses(team2Player1.getLosses()).avatarUrl(team2Player1.getAvatarUrl()).build(),
                            LoadPlayerDTO.builder().id(team2Player2.getId()).name(team2Player2.getName())
                                    .nickname(team2Player2.getNickname()).wins(team2Player2.getWins())
                                    .losses(team2Player2.getLosses()).avatarUrl(team2Player2.getAvatarUrl()).build()))
                    .build();

        } else {
            team1Player1.setLosses(team1Player1.getLosses() + 1);
            team1Player2.setLosses(team1Player2.getLosses() + 1);
            team2Player1.setWins(team2Player1.getWins() + 1);
            team2Player2.setWins(team2Player2.getWins() + 1);

            resultMatchDTO = LoadMatchDTO.builder()
                    .id(match.getId())
                    .matchDate(match.getMatchDate())
                    .team1Score(match.getTeam1Score())
                    .team2Score(match.getTeam2Score())
                    .winnerTeam(match.getWinnerTeam())
                    .loserTeamPlayers(List.of(
                            LoadPlayerDTO.builder().id(team1Player1.getId()).name(team1Player1.getName())
                                    .nickname(team1Player1.getNickname()).wins(team1Player1.getWins())
                                    .losses(team1Player1.getLosses()).avatarUrl(team1Player1.getAvatarUrl()).build(),
                            LoadPlayerDTO.builder().id(team1Player2.getId()).name(team1Player2.getName())
                                    .nickname(team1Player2.getNickname()).wins(team1Player2.getWins())
                                    .losses(team1Player2.getLosses()).avatarUrl(team1Player2.getAvatarUrl()).build()))
                    .winnerTeamPlayers(List.of(
                            LoadPlayerDTO.builder().id(team2Player1.getId()).name(team2Player1.getName())
                                    .nickname(team2Player1.getNickname()).wins(team2Player1.getWins())
                                    .losses(team2Player1.getLosses()).avatarUrl(team2Player1.getAvatarUrl()).build(),
                            LoadPlayerDTO.builder().id(team2Player2.getId()).name(team2Player2.getName())
                                    .nickname(team2Player2.getNickname()).wins(team2Player2.getWins())
                                    .losses(team2Player2.getLosses()).avatarUrl(team2Player2.getAvatarUrl()).build()))
                    .build();

        }

        this.playerRepository.save(team1Player1);
        this.playerRepository.save(team1Player2);
        this.playerRepository.save(team2Player1);
        this.playerRepository.save(team2Player2);

        return resultMatchDTO;
    }

}
