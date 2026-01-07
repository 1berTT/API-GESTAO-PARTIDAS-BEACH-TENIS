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

@Service
public class ListMatchesUseCase {

    @Autowired
    private MatchRepository matchRepository;

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
                .map(match -> LoadMatchDTO.builder()
                        .id(match.getId())
                        .matchDate(match.getMatchDate())
                        .team1Score(match.getTeam1Score())
                        .team2Score(match.getTeam2Score())
                        .winnerTeam(match.getWinnerTeam())
                        .build())
                .collect(Collectors.toList());

        return PaginatedMatchesResponseDTO.builder()
                .matches(matches)
                .currentPage(matchesPage.getNumber())
                .totalPages(matchesPage.getTotalPages())
                .totalItems(matchesPage.getTotalElements())
                .itemsPerPage(matchesPage.getSize())
                .build();
    }

}
