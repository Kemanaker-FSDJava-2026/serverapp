package com.metrodata.serverapp.config;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JWTConfig {

  @Value("${jwt.secret}")
  private String SECRET;

  // ? setup expirate
  private final long EXPIRATION = 1000 * 60 * 60 * 24; // 24 jam

  // ? check secret saat aplikasi start => minimal 32 karakter
  @PostConstruct
  public void validateSecret() {
    if (SECRET == null || SECRET.getBytes(StandardCharsets.UTF_8).length < 32) {
      throw new IllegalStateException("JWT secret minimal 32 karakter, check di file .env");
    }
  }

  // ? ambil key
  public SecretKey getSignKey() {
    return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)); // UTF-8
  }

  // ? genarte token
  public String genarateToken(String username) {
    return Jwts
        .builder()
        .subject(username)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
        .signWith(getSignKey())
        .compact();
  }

  // ? membaca username dari token
  public String extractUsername(String token) {
    Claims claims = Jwts
        .parser()
        .verifyWith(getSignKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();

    return claims.getSubject();
  }

  // ? check token isvalid
  public boolean isTokenValid(String token) {
    try {
      Jwts.parser().verifyWith(getSignKey()).build().parseSignedClaims(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
