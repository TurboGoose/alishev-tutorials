package ru.turbogoose.jwt.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String SECRET;

    public String generateToken(String username) {
        LocalDateTime tokenExpiration = LocalDateTime.now().plusMinutes(60);
        return JWT.create()
                .withSubject("User details")
                .withIssuer("turbogoose")
                .withIssuedAt(LocalDateTime.now().toInstant(ZoneOffset.UTC))
                .withExpiresAt(tokenExpiration.toInstant(ZoneOffset.UTC))
                .withClaim("username", username)
                .sign(Algorithm.HMAC256(SECRET));
    }

    public String validateTokenAndRetrieveUsername(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                .withSubject("User details")
                .withIssuer("turbogoose")
                .build();
        DecodedJWT decodedJwt = verifier.verify(token);
        return decodedJwt.getClaim("username").asString();
    }
}
