package com.Santi.demo_jwt.config;

import com.Santi.demo_jwt.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.PasswordAuthentication;

@Configuration
@RequiredArgsConstructor
//Autentication manager, para saber cual usar en el servicio de autenticación
//proveedor de acceso a datos
public class AplicationConfig {

  private final UserRepository userRepository;

  @Bean
  //Metodo para acceder a las instancias
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    //throws exception si no se encuentra el manager
    return config.getAuthenticationManager();
  }

  @Bean
  public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  @Bean
  public UserDetailsService userDetailsService(){
    return username -> userRepository.findByUsername(username)//Busca user name
        .orElseThrow(() -> new UsernameNotFoundException("Username no found"));//manejo de excepciones
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

}
//48:53