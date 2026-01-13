package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.Data;

@Data
@Entity
@Table(name = "match")
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDateTime matchDate = LocalDateTime.now();

    @Min(value = 0, message = "O número de pontos do time 1 deve ser maior ou igual a 0")
    private Integer team1Score;

    @Min(value = 0, message = "O número de pontos do time 2 deve ser maior ou igual a 0")
    private Integer team2Score;

    @Min(value = 1, message = "O número do time vencedor deve ser 1 ou 2")
    @Max(value = 2, message = "O número do time vencedor deve ser 1 ou 2")
    private Integer winnerTeam;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
