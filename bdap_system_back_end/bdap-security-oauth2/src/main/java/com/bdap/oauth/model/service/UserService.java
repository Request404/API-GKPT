package com.bdap.oauth.model.service;

import com.bdap.common.dto.BadpUserDto;
import com.bdap.common.dto.BdapUserConnectDto;
import com.bdap.oauth.model.feign.AdministrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService implements UserDetailsService {

  @Value("${feign.SecretKey}")
  private String FEIGN_SECRET_KEY;

  private final PasswordEncoder passwordEncoder;

  private final AdministrationService administrationService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      BdapUserConnectDto connectDto = new BdapUserConnectDto();
      connectDto.setUsername(username);
      connectDto.setFeignSecretKey(passwordEncoder.encode(FEIGN_SECRET_KEY));
      BadpUserDto userDto = administrationService.login(connectDto);
      if (userDto==null){
        return null;
      } else {
        return new User(userDto.getUsername(),userDto.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(userDto.getAuthority()));
      }
  }

}

