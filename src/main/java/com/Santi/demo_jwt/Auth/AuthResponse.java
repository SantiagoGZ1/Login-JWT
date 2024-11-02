package com.Santi.demo_jwt.Auth;
//Respuesta independiente de la solicitud de inicio de sesion

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
  String token;
}
