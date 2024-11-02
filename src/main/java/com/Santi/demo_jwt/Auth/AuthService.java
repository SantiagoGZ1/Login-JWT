package com.Santi.demo_jwt.Auth;

import com.Santi.demo_jwt.Jwt.JwtService;
import com.Santi.demo_jwt.user.Role;
import com.Santi.demo_jwt.user.User;
import com.Santi.demo_jwt.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final JwtService jwtService;

  public AuthResponse login(LoginRequest loginRequest) {
    return null;
  }

  public AuthResponse register(RegisterRequest registerRequest) {
    //patrón de diseño builder para la creación de objetos
    User user = User.builder()
        .username(registerRequest.getUsername())
        .password(registerRequest.getPassword())
        .firstName(registerRequest.getFirstName())
        .lastName(registerRequest.getLastName())
        .country(registerRequest.getCountry())
        .role(Role.User)
        .build();
    userRepository.save(user);
    return AuthResponse.builder().token(jwtService.getToken(user)).build();
  }
}
