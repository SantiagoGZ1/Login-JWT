package com.Santi.demo_jwt.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor

public class DemoController {

  @PostMapping(value ="demo")
  public String welcome () {
      return "Welcome from security endpoint";
  }
  
}
//por defecto spring seciruty agrega la seguridad a todas las rutas, por lo que si queremos que una ruta sea publica debemos agregarla en el archivo de configuracion de seguridad