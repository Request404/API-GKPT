package com.bdap.user.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReturn {
  private String phone;
  private String userCode;
  private Integer success;
}
