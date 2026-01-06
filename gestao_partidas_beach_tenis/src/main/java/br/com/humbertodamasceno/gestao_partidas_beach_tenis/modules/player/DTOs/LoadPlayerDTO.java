package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.DTOs;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoadPlayerDTO {
    private UUID id;
    private String name;
    private String nickname;
    private Integer wins;
    private Integer losses;
    private String avatarUrl;

}
