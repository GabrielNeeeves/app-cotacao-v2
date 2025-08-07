package com.main.app_cotacao_v2.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/listas_padrao").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/listas_padrao").hasRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.DELETE, "/listas_padrao").hasRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.PUT, "/listas_padrao").hasRole("FUNCIONARIO")

                        .requestMatchers(HttpMethod.GET, "/listas_personalizada").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/listas_personalizada").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.DELETE, "/listas_personalizada").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.PUT, "/listas_personalizada").hasRole("CLIENTE")

                        .requestMatchers("/alunos/**").hasRole("CLIENTE")

                        .requestMatchers("/empresas/**").hasRole("ADMINISTRADOR")

                        .requestMatchers("/escolas/**").hasRole("ADMINISTRADOR")

                        .requestMatchers(HttpMethod.GET, "/ofertas").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/ofertas").hasRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.DELETE, "/ofertas").hasRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.PUT, "/ofertas").hasRole("FUNCIONARIO")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
