package com.Santi.demo_jwt.Auth;

import com.Santi.demo_jwt.Jwt.JwtService;
import com.Santi.demo_jwt.user.Role;
import com.Santi.demo_jwt.user.User;
import com.Santi.demo_jwt.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;


  public AuthResponse login(LoginRequest loginRequest) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    UserDetails user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
    String token = jwtService.getToken(user);
    return AuthResponse.builder()
        .token(token)
        .build();
  }

  public AuthResponse register(RegisterRequest registerRequest) {
    //patrón de diseño builder para la creación de objetos
    User user = User.builder()
        .username(registerRequest.getUsername())
        .password(passwordEncoder.encode(registerRequest.getPassword()))
        .firstName(registerRequest.getFirstName())
        .lastName(registerRequest.lastName)
        .country(registerRequest.getCountry())
        .role(Role.User)
        .build();
    userRepository.save(user);
    return AuthResponse.builder().token(jwtService.getToken(user)).build();
  }
}
