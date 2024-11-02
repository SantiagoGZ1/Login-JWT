package com.Santi.demo_jwt.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //get y set auto
@Builder //Objetos
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
  //Se encarga de recibir los datos de la solicitud de inicio de sesion
  String username;
  String password;
}
