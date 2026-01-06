package br.com.humbertodamasceno.gestao_partidas_beach_tenis.security;

import java.io.IOException;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.providers.JWTProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (request.getRequestURI().contains("/player") || request.getRequestURI().contains("/match")) {

            if (header != null) {
                var token = this.jwtProvider.validateToken(header);

                if (token == null) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                request.setAttribute("admin_id", token.getSubject());

                var roles = token.getClaim("roles").asList(Object.class);

                var grants = roles.stream().map(
                        role -> new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase())).toList();

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        token.getSubject(), null, grants);

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        }

        filterChain.doFilter(request, response);

    }

}
