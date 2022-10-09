package st.cri.demoweather.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import st.cri.demoweather.web.security.PasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DemoWeatherUserDetailsService implements UserDetailsService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public DemoWeatherUserDetailsService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<st.cri.demoweather.model.User> user = userService.findByNickname(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found by name: " + username);
        }
        return new User(user.get().getNickname(), user.get().getPassword(), new ArrayList<>());
    }

    private String passwordEncoded(String rawPassword) {
        return passwordEncoder.encoder().encode(rawPassword);
    }
}
