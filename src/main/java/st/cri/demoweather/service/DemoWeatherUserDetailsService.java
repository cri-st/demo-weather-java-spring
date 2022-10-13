package st.cri.demoweather.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * The Demo weather user details service.
 */
@Service
public class DemoWeatherUserDetailsService implements UserDetailsService {

  private final UserService userService;

  /**
   * Instantiates a new Demo weather user details service.
   *
   * @param userService the user service
   */
  public DemoWeatherUserDetailsService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final Optional<st.cri.demoweather.model.User> optionalUser = userService.findByNickname(username);
    if (optionalUser.isEmpty()) {
      throw new UsernameNotFoundException("User not found by name: " + username);
    }
    final st.cri.demoweather.model.User user = optionalUser.get();
    return User.withUsername(user.getNickname())
        .password(user.getPassword())
        .disabled(false)
        .authorities(getAuthorities(user))
        .build();
  }

  private Collection<GrantedAuthority> getAuthorities(st.cri.demoweather.model.User user) {
    final Collection<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(user.getRole().name().toUpperCase()));

    return authorities;
  }
}
