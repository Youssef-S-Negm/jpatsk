package com.youssef.jpatsk.security;

import com.youssef.jpatsk.security.filter.ValidationReportFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    @Bean
    @Order(1)
    public SecurityFilterChain noAuthSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/courses")
                .authorizeHttpRequests(request ->
                        request.anyRequest().permitAll()
                );

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain authSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers("/courses/add",
                                        "/courses/update/**",
                                        "/courses/delete/**").authenticated()
                                .anyRequest().denyAll()
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterAfter(new ValidationReportFilter(), AnonymousAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                         PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }
}
