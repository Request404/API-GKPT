package com.bdap.common.dto;

import lombok.Data;

@Data
public class BadpUserDto {
  private String username;
  private String password;
  private String authority;
}
