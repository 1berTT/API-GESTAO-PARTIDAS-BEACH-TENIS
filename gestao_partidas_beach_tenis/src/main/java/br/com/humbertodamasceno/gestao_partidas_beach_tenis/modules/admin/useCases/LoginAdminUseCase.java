package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.admin.useCases;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.admin.DTOs.AdminLoginRequestDTO;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.admin.DTOs.AdminLoginResponseDTO;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.admin.repositories.AdminRepositoty;

@Service
public class LoginAdminUseCase {

    @Value("${security.token.secret.admin}")
    private String secretKey;

    @Autowired
    AdminRepositoty adminRepositoty;

    @Autowired
    PasswordEncoder passwordEncoder;

    public AdminLoginResponseDTO execute(AdminLoginRequestDTO adminLoginRequestDTO) {

        var admin = this.adminRepositoty.findByUsername(adminLoginRequestDTO.username()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Username/password incorrect");
        });

        var passwordMatches = this.passwordEncoder.matches(adminLoginRequestDTO.password(), admin.getPassword());

        if (!passwordMatches) {
            throw new UsernameNotFoundException("Username/password incorrect");
        }

        Algorithm algorithm = Algorithm.HMAC256(this.secretKey);
        var expiresIN = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create()
                .withIssuer("gestao_partidas_beach_tenis")
                .withSubject(admin.getId().toString())
                .withClaim("roles", Arrays.asList("ADMIN"))
                .withExpiresAt(expiresIN)
                .sign(algorithm);

        var adminLoginResponseDTO = new AdminLoginResponseDTO().builder().access_token(token)
                .expires_in(expiresIN.toEpochMilli()).build();

        return adminLoginResponseDTO;

    }

}
