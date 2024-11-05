package com.Santi.demo_jwt.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
//No se pueden repetir los nombres de usuario
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class User implements UserDetails {
  @Id
      @GeneratedValue
  Integer id;
  @Column(nullable = false) //No puede ser nulo
  String username;
  String password;
  String firstName;
  String lastName;
  String country;
  @Enumerated(EnumType.STRING) //sirve para que se guarde el nombre del enum en la base de datos
  Role role;

  //UserDetails implementation
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    //Lista que representa los roles del usuario (autoridad)
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
