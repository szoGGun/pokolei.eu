package eu.przemyslawrutkowski.pokolei.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/**", "/login").permitAll()
                        .requestMatchers("/admin").hasRole("ROLE_ADMIN"))
                .formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/admin")
                .failureUrl("/")
                .and()
                .logout().logoutSuccessUrl("/");
        return http.build();
    }
}


