package com.linkedin.config.security;

import com.linkedin.entities.Login;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

  private static final long serialVersionUID = 1L;

  private final Login login;

  public MyUserDetails(Login login) {
    this.login = login;
  }

  @Override
  public String getUsername() {
    return login.getUsername();
  }

  @Override
  public String getPassword() {
    return login.getPassword();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    final List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(login.getRole().name()));
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return login.getActive();
  }

  public Login getLogin() {
    return login;
  }


}
