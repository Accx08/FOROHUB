package com.aluracursos.foro_hub.api.infra.security;

import com.aluracursos.foro_hub.api.domain.usuario.Usuario;
import com.aluracursos.foro_hub.api.domain.usuario.UsuarioRepository;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Value("${api.security.token.secret}")
    private String secret;

    public String generarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Foro-hub")
                    .withSubject(usuario.getCorreoElectronico())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("error al generar el  token jwt", exception);
        }
    }

    public String getSubject(String tokenJWT) {

        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API Foro-hub")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido o expirado!");
        }
    }

    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-04:00"));
    }

    public String extractUsername(String tokenJWT) {
        DecodedJWT jwt = JWT.decode(tokenJWT);
        return jwt.getSubject();
    }

    public Long extraeIdDelToken(String userName){
        //entra username con mail
       // System.out.println(userName+"Este es el valor de username:AQUI ESTA");
        var userNameId = usuarioRepository.findByCorreoElectronico(userName);
        //System.out.println(userNameId);
        var usuarioId = userNameId.getId();
        //System.out.println(usuarioId+"Este es la var USUARIO");
        //aqui extraigo el Id del token
        return usuarioId;
    }


}
