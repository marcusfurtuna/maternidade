package com.clinica.maternidade.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

@Component
public class JwtService {
  @Value("${jwt.secret}")
  private String secret;
  @Value("${jwt.expiration}")
  private long expiration;

  public String generateToken(String username, List<String> roles) {
    return Jwts.builder()
      .setSubject(username)
      .claim("roles", roles)
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + expiration))
      .signWith(io.jsonwebtoken.security.Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
      .compact();
  }

  public Jws<Claims> parse(String token) {
    return Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token);
  }
}