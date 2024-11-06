package com.Santi.demo_jwt.Auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthController {

  //Acceso a metodos de login y response para acceder al token
  private final AuthService authService;

  @PostMapping("/login")
  //responseEntity: Representa las respuestas http de forma flexible
  public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
    return ResponseEntity.ok(authService.login(loginRequest));
  }

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
      return ResponseEntity.ok(authService.register(registerRequest));
  }
  

}
