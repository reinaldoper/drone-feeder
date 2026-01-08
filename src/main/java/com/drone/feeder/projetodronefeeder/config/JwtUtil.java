package com.drone.feeder.projetodronefeeder.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 * Classe utilitária para geração de tokens JWT.
 */
@Component
public class JwtUtil {

  /**
   * Gera um token JWT com base no e-mail do usuário.
   *
   * @param email e-mail do usuário
   * @return token JWT gerado
   */
  public String generateToken(String email) {
    String secretKey = System.getenv("SECRET_KEY");
    return Jwts.builder()
        .setSubject(email)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }
}
