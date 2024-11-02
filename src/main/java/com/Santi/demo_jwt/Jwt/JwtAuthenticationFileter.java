package com.Santi.demo_jwt.Jwt;

import java.io.IOException;

import ch.qos.logback.core.util.StringUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFileter extends OncePerRequestFilter {
  //OnePerRequestFilter: clase abstracta para crear filtros personalizados
  //El filtro se ejecuta una vez por cada solicitud http
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) //Metodo para realizar todos los filtros al token
      throws ServletException, IOException {
    //Obtiene el token de la solicitud
    final String token = getTokenFromRequest(request);
    if (token == null) {
      filterChain.doFilter(request, response);
      return;
    }
    filterChain.doFilter(request, response);
  }

  private String getTokenFromRequest(HttpServletRequest request) {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    //StringUtils: Libreria para manejar cadenas de texto
    if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer")){
      //Verifica si el texto existe y evaluar si empieza con "Bearer"
      //Si es verdadero, despues de "Bearer" se obtiene el token
      return authHeader.substring(7); //desde la posicion 7 hasta el final extrae el token
    }
    return null;
  }
}
