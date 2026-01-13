package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.matchTeam.entities;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.entities.MatchEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "match_team")
public class MatchTeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private MatchEntity match;

    @Min(value = 1, message = "O número do time deve ser 1 ou 2")
    @Max(value = 2, message = "O número do time deve ser 1 ou 2")
    private Integer teamNumber;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
