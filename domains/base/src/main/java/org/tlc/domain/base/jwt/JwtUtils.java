package org.tlc.domain.base.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tlc.domain.base.exceptions.BadCredentialsException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public final class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${auth.jwt.secret}") private String jwtSecret;
    @Value("${auth.jwt.expiration.in.minutes}") private int jwtExpirationMs;
    @Value("${auth.refresh.jwt.expiration.in.minutes}") private int refreshJwtExpirationMs;

    public String generateJwtToken(String email, boolean isRefresh) {
        int exp = isRefresh ? refreshJwtExpirationMs : jwtExpirationMs;
        return JWT.create().withSubject(email)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + exp))
                .sign(Algorithm.HMAC512(jwtSecret));
    }

    public String generateJwtToken(String email, UUID id, boolean isRefresh) {
        int exp = isRefresh ? refreshJwtExpirationMs : jwtExpirationMs;
        return JWT.create().withSubject(email).withPayload(Map.of("id", id.toString()))
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + exp))
                .sign(Algorithm.HMAC512(jwtSecret));
    }

    public boolean validateJwtToken(String jwt){
        try {
            JWT.create().withJWTId(jwtSecret).withNullClaim(jwt);
            return true;
        } catch (JWTVerificationException e) {
            throw new BadCredentialsException("Invalid JWT token: "+e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("JWT claims string is empty: " + e.getMessage());
        }
    }

    public String getEmailFromJwtToken(String jwt) {
        return JWT.decode(jwt).getSubject();
    }
}
