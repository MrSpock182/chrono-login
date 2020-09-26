package com.github.mrspock182.chrono.login.configuration;

import com.github.mrspock182.chrono.login.domain.enumerable.JwtRoles;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private final JwConfiguration jwConfiguration;

    public AuthenticationManager(JwConfiguration jwConfiguration) {
        this.jwConfiguration = jwConfiguration;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();

        String username;
        try {
            username = jwConfiguration.getUsernameFromToken(token);
        } catch (Exception e) {
            username = null;
        }
        if (username != null && jwConfiguration.validateToken(token)) {
            Claims claims = jwConfiguration.getAllClaimsFromToken(token);
            List<String> rolesMap = claims.get("role", List.class);
            List<JwtRoles> roles = new ArrayList<>();

            for (String rolemap : rolesMap) {
                roles.add(JwtRoles.valueOf(rolemap));
            }

            return Mono.just(new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    roles.stream().map(authority -> new SimpleGrantedAuthority(authority.name()))
                            .collect(Collectors.toList())));
        } else {
            return Mono.empty();
        }
    }
}
