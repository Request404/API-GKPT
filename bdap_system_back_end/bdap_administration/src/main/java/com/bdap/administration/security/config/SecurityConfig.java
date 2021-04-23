/*
 * @Copyright (c) 2020.
 * @Company：国科（广东）信息技术服务有限公司
 * @Author：GuoKeGD
 * @Version：1.2.0
 */

package com.bdap.administration.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  //排除swagger拦截
  public static final String[] SWAGGER_WHITELIST = {
      "/swagger-ui.html",
      "/swagger-ui/*",
      "/swagger-resources/**",
      "/v2/api-docs",
      "/v3/api-docs",
      "/webjars/**"
  };

  //密码编码器
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  //认证管理器
  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  //安全拦截机制
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and()
        .csrf().disable()
        .authorizeRequests()
        .antMatchers(SWAGGER_WHITELIST).permitAll()
        .antMatchers("/admin-account/login").permitAll()
        .anyRequest().authenticated();
  }
}
