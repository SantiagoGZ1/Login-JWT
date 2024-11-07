package com.Santi.demo_jwt.Jwt;

import com.Santi.demo_jwt.user.User;
import io.jsonwebtoken.Claims;
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
import java.util.function.Function;

@Service

public class JwtService {

  //Sin caracateres espciales ya que se usa base64 y no afecta a la url
  private static final String SecretKey = "QnFybGZMVzJmbkpFclpGQ3NyZzVZSkpINzkwbkVzWTM=";
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

  public String getUsernameFromToken(String token) {
    return getClaim(token, Claims::getSubject);
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    //verificar que el username que extraemos corresponde al usuario que se encuentra en la base de datos
    final String username = getUsernameFromToken(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  private Claims getAllClaims(String token) { //Claims: datos que se guardadn en el token
    return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJwt(token).getBody();
  }

  public <T> T getClaim (String token, Function<Claims, T> claimsResover){
    final Claims claims = getAllClaims(token); //obtener todos los claims
    return claimsResover.apply(claims);//aplicar la funcion
  }

  //Obtener la fecha de expiración
  private Date getExpiration(String token) {
    return getClaim(token, Claims::getExpiration);
  }

  //Verificar si el token ha expirado
  private boolean isTokenExpired(String token) {
    return getExpiration(token).before(new Date());
  }
}
