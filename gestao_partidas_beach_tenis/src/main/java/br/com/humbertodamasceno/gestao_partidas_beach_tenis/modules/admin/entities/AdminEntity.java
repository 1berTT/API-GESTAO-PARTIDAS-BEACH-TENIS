package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.admin.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "admin")
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Length(min = 3, max = 255, message = "O nome deve ter entre 3 e 255 caracteres")
    private String name;

    @NotBlank()
    @Pattern(regexp = "^[^\\s]+$", message = "O username não pode conter espaços")
    private String username;

    @Email(message = "O email deve ser válido")
    private String email;

    @Length(min = 8, max = 255, message = "A senha deve ter entre 8 e 255 caracteres")
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
