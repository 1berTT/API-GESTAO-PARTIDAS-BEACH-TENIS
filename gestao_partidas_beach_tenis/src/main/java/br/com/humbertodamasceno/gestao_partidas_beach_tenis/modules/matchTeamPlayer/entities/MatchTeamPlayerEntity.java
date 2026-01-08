package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.matchTeamPlayer.entities;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.UUID;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.matchTeam.entities.MatchTeamEntity;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.entities.PlayerEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "match_team_player")
public class MatchTeamPlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "match_team_id")
    private MatchTeamEntity matchTeam;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private PlayerEntity player;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
