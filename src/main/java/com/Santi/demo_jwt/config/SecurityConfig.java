package com.Santi.demo_jwt.config;

import com.Santi.demo_jwt.Jwt.JwtAuthenticationFileter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // Anotación que indica que esta clase es una clase de configuración
@EnableWebSecurity // Anotación que habilita la seguridad web
@RequiredArgsConstructor

public class SecurityConfig {

  private final JwtAuthenticationFileter jwtAuthenticationFileter;
  private final AuthenticationProvider authProvider;
  // security filter chain: cadena de filtros que se ejecutan

  // Configuración de endpoints públicos y privados

  // Metodo que restringe el acceso a los endpoints

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(authorizeRequests -> 
        authorizeRequests
          .requestMatchers("/auth/**").permitAll() 
          .anyRequest().authenticated()
      )
        //.formLogin(withDefaults()) autenticación de spring security
        // Autenticación basada en JWT...
        .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//politica de creación de sesiones y no las usa
        .authenticationProvider(authProvider)
        .addFilterBefore(jwtAuthenticationFileter, UsernamePasswordAuthenticationFilter.class)
      .build();
      //autoriza a todos los usuarios a acceder a la ruta /auth/**, las demas rutas estan protegidas y muestra un formulario de login por defecto
  }
}
