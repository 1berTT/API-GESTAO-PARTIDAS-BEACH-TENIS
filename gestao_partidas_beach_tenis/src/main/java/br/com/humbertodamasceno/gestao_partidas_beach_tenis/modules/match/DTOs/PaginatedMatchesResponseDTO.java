package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.DTOs;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedMatchesResponseDTO {
    private List<LoadMatchDTO> matches;
    private int currentPage;
    private int totalPages;
    private long totalItems;
    private int itemsPerPage;
}
