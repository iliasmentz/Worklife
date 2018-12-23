package com.linkedin.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  @Value("${linkedin.oauth.clientId}")
  private String clientId;
  @Value("${linkedin.oauth.clientSecret}")
  private String clientSecret;


  private final PasswordEncoder passwordEncoder;

  private final AuthenticationManager authManager;

  @Autowired
  public AuthorizationServerConfig(PasswordEncoder passwordEncoder, @Qualifier("authenticationManagerBean") AuthenticationManager authManager) {
    this.passwordEncoder = passwordEncoder;
    this.authManager = authManager;
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpointConfigurer) {
    endpointConfigurer.tokenStore(tokenStore())
        .authenticationManager(authManager);
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clientConfigurer) throws Exception {
    String secretEncoded = passwordEncoder.encode(clientSecret);
    clientConfigurer.inMemory()
        .withClient(clientId).secret(secretEncoded)
        .authorizedGrantTypes("password")
        //.authorities("ROLE_TRUSTED_CLIENT")
        .scopes("read", "write");

  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
    oauthServer
        .tokenKeyAccess("permitAll()")
        .checkTokenAccess("isAuthenticated()");

  }

  @Bean
  public TokenStore tokenStore() {
    return new InMemoryTokenStore();
  }


}
