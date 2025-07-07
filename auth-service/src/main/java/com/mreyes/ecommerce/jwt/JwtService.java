package com.mreyes.ecommerce.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  private static final String SECRET = "9aae4d0bfd46380c9aae4d0bfd46380c"; // must be 256-bit for HS256

  private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

  public String generateToken(String username, String role) {
    return Jwts.builder().setSubject(username).claim("role", role).setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
        .signWith(key, SignatureAlgorithm.HS256).compact();
  }

  public Claims extractClaims(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
  }

  public String extractUsername(String token) {
    return extractClaims(token).getSubject();
  }

  public String extractRole(String token) {
    return extractClaims(token).get("role", String.class);
  }
}
