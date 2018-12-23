package com.linkedin.config.security;

import com.linkedin.entities.Login;
import com.linkedin.entities.repo.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final LoginRepository userRepository;

  @Autowired
  public UserDetailsServiceImpl(LoginRepository userRepository) {
    super();
    this.userRepository = userRepository;
  }


  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    Login user = userRepository.findByUsernameIgnoreCase(username);

    if (user == null) {
      throw new UsernameNotFoundException(username);
    }

    return new MyUserDetails(user);
  }

}
