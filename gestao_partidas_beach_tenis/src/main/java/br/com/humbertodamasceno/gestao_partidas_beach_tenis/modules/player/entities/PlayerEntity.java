package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.player.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Min;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "player")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Length(min = 3, max = 255, message = "O nome deve ter entre 3 e 255 caracteres")
    private String name;

    @NotBlank()
    @Pattern(regexp = "^[^\\s]+$", message = "O nickname não pode conter espaços")
    private String nickname;

    @Min(value = 0, message = "O número de vitórias deve ser maior ou igual a 0")
    private Integer wins = 0;

    @Min(value = 0, message = "O número de derrotas deve ser maior ou igual a 0")
    private Integer losses = 0;

    private String avatarUrl; // Caminho ou URL da foto do avatar

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
