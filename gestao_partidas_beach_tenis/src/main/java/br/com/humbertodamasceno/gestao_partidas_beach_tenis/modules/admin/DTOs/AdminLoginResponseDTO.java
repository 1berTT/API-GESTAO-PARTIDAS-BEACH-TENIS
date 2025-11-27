package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.admin.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminLoginResponseDTO {
    private String access_token;
    private Long expires_in;
}
