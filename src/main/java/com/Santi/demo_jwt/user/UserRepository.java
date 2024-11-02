package com.Santi.demo_jwt.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
  //query method: metodos de busqueda especificos
  //query para buscar por userName
  Optional<User> findByUsername(String username);
}
