package com.project.app.conf;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.project.app.model.User;

@Service
public class TokenServices {

    @Value("${api.sec.token.auth}")
    private String secret;

    public String generetdTokken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String tokken = JWT.create()
                    .withIssuer("Auth-api")
                    .withSubject(user.getLogin())
                    .withExpiresAt(expDate())
                    .sign(algorithm);

            return tokken;

        } catch (JWTCreationException ex) {
            throw new RuntimeException("Error whlie generating tokken : " + ex);
        }
    }

    private Instant expDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validaTokker(String tokker) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
             .withIssuer("Auth-api")
             .build()
             .verify(tokker)
             .getSubject();
        } catch (JWTVerificationException ex) {
            return "";
        }
    }

}
