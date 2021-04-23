package com.bdap.common.dto;

import lombok.Data;

@Data
public class BdapUserConnectDto {
  String username;
  String feignSecretKey;
}
