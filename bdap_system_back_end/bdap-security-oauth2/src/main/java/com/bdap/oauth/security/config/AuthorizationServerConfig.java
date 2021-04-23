package com.bdap.oauth.security.config;

import com.bdap.oauth.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private ClientDetailsService clientDetailsService;

  @Autowired
  private AuthorizationCodeServices authorizationCodeServices;

  @Autowired
  private UserService userService;


  @Autowired
  @Qualifier("jwtTokenStore")
  private TokenStore tokenStore;

  @Autowired
  private JwtAccessTokenConverter jwtAccessTokenConverter;

  @Bean
  public ClientDetailsService clientDetailsService(DataSource dataSource) {
    JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
    clientDetailsService.setPasswordEncoder(passwordEncoder);
    return clientDetailsService;
  }

  @Bean
  public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
    return new JdbcAuthorizationCodeServices(dataSource);//设置授权码模式的授权码如何存取
  }


  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.withClientDetails(clientDetailsService);
  }

  @Bean
  public AuthorizationServerTokenServices tokenServices() {
    DefaultTokenServices services = new DefaultTokenServices();

    TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();

    tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter));
    services.setClientDetailsService(clientDetailsService);
    services.setSupportRefreshToken(true);
    services.setTokenStore(tokenStore);//令牌存储策略
    services.setTokenEnhancer(tokenEnhancerChain);//设置令牌增强
    services.setAccessTokenValiditySeconds(1800); //令牌有效期1小时
    services.setRefreshTokenValiditySeconds(259200); //刷新令牌默认有效期3天
    return services;
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authenticationManager(authenticationManager)
        .authorizationCodeServices(authorizationCodeServices)
        .userDetailsService(userService)
        .tokenServices(tokenServices());
//        .allowedTokenEndpointRequestMethods(HttpMethod.POST);
  }

  //令牌访问端点的安全认证
  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()")
        .allowFormAuthenticationForClients();
  }


}