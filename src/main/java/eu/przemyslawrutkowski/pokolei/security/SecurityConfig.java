package eu.przemyslawrutkowski.pokolei.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf().disable()
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

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:8080");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        return request -> corsConfiguration;
    }
}


