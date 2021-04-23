/*
 * @Copyright (c) 2020.
 * @Company：国科（广东）信息技术服务有限公司
 * @Author：GuoKeGD
 * @Version：1.2.0
 */

package com.bdap.ott.security.service.impl;


import com.bdap.ott.security.service.UserVerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserVerifyServiceImpl implements UserVerifyService {

  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return new User(username, passwordEncoder.encode("GuoKeGD"), AuthorityUtils.commaSeparatedStringToAuthorityList(""));
  }
}
