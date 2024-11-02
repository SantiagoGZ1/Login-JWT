package com.Santi.demo_jwt.Jwt;

import com.Santi.demo_jwt.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@Service

public class JwtService {

  private static final String SecretKey = "S3cureK3yWith$pec!alChar@ctersAndLength";
  //User implementa a UserDetails
  public String getToken(UserDetails userDetails){
    //Generar el token
    return getToken(new HashMap<>(), userDetails);
  }

  private String getToken(HashMap<String, Object> extraClaims, UserDetails userDetails) {
    //JWTS para generar token
    return Jwts
        .builder()
        .setClaims(extraClaims)//setClaims: datos que se quieren guardar en el token
        .setSubject(userDetails.getUsername())//username
        .setIssuedAt(new Date(System.currentTimeMillis()))//Fecha creación token
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))//Fecha expiración token de un dia
        .signWith(getKey(), SignatureAlgorithm.HS256)//Objeto key y algoritmo de encriptación a usar
        .compact();
  }

  private Key getKey() {
    //pasar la key a base 64 para mandarla como firma del token
    byte[]keyBytes = Decoders.BASE64.decode(SecretKey);
    return Keys.hmacShaKeyFor(keyBytes);//instancia de secret key
  }
}
