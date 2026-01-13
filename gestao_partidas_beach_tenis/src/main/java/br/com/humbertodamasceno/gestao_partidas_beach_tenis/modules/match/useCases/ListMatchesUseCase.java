package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.repositories.MatchRepository;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.entities.MatchEntity;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.DTOs.LoadMatchDTO;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.DTOs.PaginatedMatchesResponseDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.matchTeamPlayer.repositories.MatchTeamPlayerRepository;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.matchTeam.repositories.MatchTeamRepository;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.DTOs.LoadPlayerDTO;
import java.util.List;
import java.util.ArrayList;

@Service
public class ListMatchesUseCase {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private MatchTeamPlayerRepository matchTeamPlayerRepository;

    @Autowired
    private MatchTeamRepository matchTeamRepository;

    public PaginatedMatchesResponseDTO execute(Pageable pageable, LocalDate date) {
        // Cria um Pageable com ordenação por data (mais recente primeiro)
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "matchDate"));

        Page<MatchEntity> matchesPage;

        // Se uma data foi fornecida, filtra por ela (busca todo o dia)
        if (date != null) {
            LocalDateTime startOfDay = date.atStartOfDay();
            LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
            matchesPage = this.matchRepository.findByMatchDateBetween(startOfDay, endOfDay, sortedPageable);
        } else {
            // Se não, retorna todos
            matchesPage = this.matchRepository.findAll(sortedPageable);
        }

        var matches = matchesPage.getContent().stream()
                .map((match) -> {

                    var team1 = this.matchTeamRepository.findByMatch_IdAndTeamNumber(match.getId(), 1);
                    var team2 = this.matchTeamRepository.findByMatch_IdAndTeamNumber(match.getId(), 2);

                    var team1Players = this.matchTeamPlayerRepository.findByMatchTeam_Id(team1.get().getId());
                    var team2Players = this.matchTeamPlayerRepository.findByMatchTeam_Id(team2.get().getId());

                    List<LoadPlayerDTO> winnerTeamPlayers = new ArrayList<>();
                    List<LoadPlayerDTO> loserTeamPlayers = new ArrayList<>();

                    if (match.getWinnerTeam() == 1) {
                        team1Players.forEach((player) -> {
                            winnerTeamPlayers.add(LoadPlayerDTO.builder()
                                    .id(player.getPlayer().getId())
                                    .name(player.getPlayer().getName())
                                    .nickname(player.getPlayer().getNickname())
                                    .wins(player.getPlayer().getWins())
                                    .losses(player.getPlayer().getLosses())
                                    .avatarUrl(player.getPlayer().getAvatarUrl()).build());
                        });
                        team2Players.forEach((player) -> {
                            loserTeamPlayers.add(LoadPlayerDTO.builder()
                                    .id(player.getPlayer().getId())
                                    .name(player.getPlayer().getName())
                                    .nickname(player.getPlayer().getNickname())
                                    .wins(player.getPlayer().getWins())
                                    .losses(player.getPlayer().getLosses())
                                    .avatarUrl(player.getPlayer().getAvatarUrl()).build());
                        });
                    } else {
                        team2Players.forEach((player) -> {
                            winnerTeamPlayers.add(LoadPlayerDTO.builder()
                                    .id(player.getPlayer().getId())
                                    .name(player.getPlayer().getName())
                                    .nickname(player.getPlayer().getNickname())
                                    .wins(player.getPlayer().getWins())
                                    .losses(player.getPlayer().getLosses())
                                    .avatarUrl(player.getPlayer().getAvatarUrl()).build());
                        });
                        team1Players.forEach((player) -> {
                            loserTeamPlayers.add(LoadPlayerDTO.builder()
                                    .id(player.getPlayer().getId())
                                    .name(player.getPlayer().getName())
                                    .nickname(player.getPlayer().getNickname())
                                    .wins(player.getPlayer().getWins())
                                    .losses(player.getPlayer().getLosses())
                                    .avatarUrl(player.getPlayer().getAvatarUrl()).build());
                        });
                    }

                    return LoadMatchDTO.builder()
                            .id(match.getId())
                            .matchDate(match.getMatchDate())
                            .team1Score(match.getTeam1Score())
                            .team2Score(match.getTeam2Score())
                            .winnerTeam(match.getWinnerTeam())
                            .winnerTeamPlayers(winnerTeamPlayers)
                            .loserTeamPlayers(loserTeamPlayers)
                            .build();
                }).collect(Collectors.toList());

        return PaginatedMatchesResponseDTO.builder()
                .matches(matches)
                .currentPage(matchesPage.getNumber())
                .totalPages(matchesPage.getTotalPages())
                .totalItems(matchesPage.getTotalElements())
                .itemsPerPage(matchesPage.getSize())
                .build();
    }

}
