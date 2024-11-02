package com.Santi.demo_jwt.Jwt;

import com.Santi.demo_jwt.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service

public class JwtService {
  //User implementa a UserDetails
  public String getToken(UserDetails userDetails){
    return null;
  }
}
