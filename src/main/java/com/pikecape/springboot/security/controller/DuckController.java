package com.pikecape.springboot.security.controller;

import com.pikecape.springboot.security.exception.NotFoundException;
import com.pikecape.springboot.security.model.Duck;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ducks")
public class DuckController {
  @GetMapping
  @RolesAllowed("admin")
  public List<Duck> getDucks() {
    return ducks;
  }

  @GetMapping("/{uid}")
  @RolesAllowed("user")
  public Duck getDuck(@PathVariable String uid) {
    return ducks.stream()
      .filter(duck -> duck.getUid().equals(uid))
      .findFirst()
      .orElseThrow(
        () -> new NotFoundException(String.format("Duck %s not found", uid))
      );
  }

  private final List<Duck> ducks = List.of(
    Duck.builder()
      .uid("1")
      .name("Duey")
      .build(),
    Duck.builder()
      .uid("2")
      .name("Huey")
      .build(),
    Duck.builder()
      .uid("3")
      .name("Luey")
      .build()
  );
}