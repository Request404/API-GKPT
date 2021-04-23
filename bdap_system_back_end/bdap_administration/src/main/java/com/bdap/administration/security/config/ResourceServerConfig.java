package com.bdap.administration.security.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  public static final String RESOURCE_ID = "bdap_administration";

  @Qualifier("jwtTokenStore")
  private final TokenStore tokenStore;

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) {
    resources.resourceId(RESOURCE_ID)//资源 id
        .tokenStore(tokenStore)
        .stateless(true);
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers(SecurityConfig.SWAGGER_WHITELIST).permitAll()
        .antMatchers("/admin-account/login").permitAll()
        .antMatchers("/admin-account/sms/sendCode").permitAll()
        .antMatchers("/admin-account/password/update").permitAll()
        .antMatchers("/**").access("#oauth2.hasScope('ROLE_ADMIN')")
        .and().csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }
}
