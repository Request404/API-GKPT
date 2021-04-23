package com.bdap.user.security.config;


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

  public static final String RESOURCE_ID = "bdap_user_operate";

  @Qualifier("jwtTokenStore")
  private final TokenStore tokenStore;

  private static final String[] SWAGGER_WHITELIST = {
      "/swagger-ui.html",
      "/swagger-ui/*",
      "/swagger-resources/**",
      "/v2/api-docs",
      "/v3/api-docs",
      "/webjars/**"
  };

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) {
    resources.resourceId(RESOURCE_ID)//资源 id
        .tokenStore(tokenStore)
        .stateless(true);
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers(SWAGGER_WHITELIST).permitAll()
        .antMatchers("/**").access("#oauth2.hasAnyScope('ROLE_ADMIN,ROLE_USER')")
        .and().csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }
}
