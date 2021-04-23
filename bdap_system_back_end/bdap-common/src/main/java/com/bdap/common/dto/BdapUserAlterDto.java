package com.bdap.common.dto;

import lombok.Data;

@Data
public class BdapUserAlterDto {
  private String user;
  private String password;
  private String verifyCode;
}
