package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.admin.entities;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AdminEntity {

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

}
