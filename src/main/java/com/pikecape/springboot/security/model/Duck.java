package com.pikecape.springboot.security.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Duck {
  private String uid;
  private String name;
}
