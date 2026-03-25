package com.portfolio.ebookshop.security;

import org.springframework.beans.factory.annotation.Value;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${app.jwt.secret}")
    private String secretKey;
    @Value("${app.jwt.issuer}")
    private String issuer;
    private final long EXPIRATION_TIME = 86400000;

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secretKey);
    }

    //CREATE TOKEN
    public String generateToken(String email) {
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(email)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(getAlgorithm());
    }

    //VALIDATE TOKEN
    public String validateAndGetEmail(String token) {
        try {
            JWTVerifier verifier = JWT.require(getAlgorithm())
                    .withIssuer(issuer)
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);

            return decodedJWT.getSubject();
        } catch (Exception e) {
            return null; 
        }
    }
}