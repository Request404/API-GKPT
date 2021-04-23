/*
 * @Copyright (c) 2020.
 * @Company：国科（广东）信息技术服务有限公司
 * @Author：GuoKeGD
 * @Version：1.2.0
 */

package com.bdap.user.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  //密码编码器
  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  //认证管理器
  public AuthenticationManager authenticationManager() throws Exception{
    return super.authenticationManager();
  }

  //安全拦截机制
  @Override
  protected void configure(HttpSecurity http) throws Exception{
    http.cors().and()
        .csrf().disable()
        .authorizeRequests()
        .anyRequest().permitAll();
  }
}
