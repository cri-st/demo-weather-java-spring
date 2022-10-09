package st.cri.demoweather.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import st.cri.demoweather.service.DemoWeatherUserDetailsService;

@EnableWebSecurity
public class SecurityConfiguration {
    private final DemoWeatherUserDetailsService demoWeatherUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfiguration(DemoWeatherUserDetailsService demoWeatherUserDetailsService, PasswordEncoder passwordEncoder) {
        this.demoWeatherUserDetailsService = demoWeatherUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(demoWeatherUserDetailsService)
                .passwordEncoder(passwordEncoder.encoder())
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/weather").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .httpBasic();
        http.csrf().disable();
        return http.build();
    }
}
