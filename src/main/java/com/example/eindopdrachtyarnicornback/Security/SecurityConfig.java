package com.example.eindopdrachtyarnicornback.Security;

import com.example.eindopdrachtyarnicornback.Repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public SecurityConfig(JwtService service, UserRepository userRepos) {
        this.jwtService = service;
        this.userRepository = userRepos;
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService udService, PasswordEncoder passwordEncoder) throws Exception {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(udService);
        return new ProviderManager(auth);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService(this.userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth

//                        .requestMatchers("/*").hasRole("ADMIN")
//                        .requestMatchers("/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/review").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.POST, "/review").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.GET, "/roles").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/users").permitAll()
                                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                                .requestMatchers(HttpMethod.GET, "/reservation").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/reservation").hasAnyRole("ADMIN", "USER")
                                .anyRequest().permitAll()

                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}