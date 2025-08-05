package com.example.hotel.HotelManagement.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.hotel.HotelManagement.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String chaveSecreta;

    public String generateToken(Usuario usuario) {
        try {
            Instant tempoExpiracao = LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
            return JWT.create().withIssuer("HotelManagement").withSubject(usuario.getUsername()).withExpiresAt(tempoExpiracao)
                    .sign(Algorithm.HMAC256(chaveSecreta));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Não foi possível gerar o JWT", e);
        }
    }

    public String validateToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(chaveSecreta))
                    .withIssuer("HotelManagement")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token inválido", e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}

