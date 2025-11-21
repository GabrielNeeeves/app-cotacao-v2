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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/listas_padrao/**").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/listas_padrao").hasRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.DELETE, "/listas_padrao/**").hasRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.PUT, "/listas_padrao").hasRole("FUNCIONARIO")

                        .requestMatchers(HttpMethod.GET, "/alunos/**").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/alunos").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.DELETE, "/alunos/**").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.PUT, "/alunos").hasRole("FUNCIONARIO")

                        .requestMatchers(HttpMethod.GET, "/clientes").hasRole("CLIENTE")

                        .requestMatchers(HttpMethod.GET, "/listas_personalizada").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/listas_personalizada").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.DELETE, "/listas_personalizada/**").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.PUT, "/listas_personalizada").hasRole("CLIENTE")

                        .requestMatchers("/alunos/**").hasRole("CLIENTE")

                        .requestMatchers(HttpMethod.GET, "/escolas/**").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/escolas/**").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/escolas/**").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT, "/escolas/**").hasRole("ADMINISTRADOR")

                        .requestMatchers(HttpMethod.GET, "/ofertas").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/ofertas").hasRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.DELETE, "/ofertas/**").hasRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.PUT, "/ofertas").hasRole("FUNCIONARIO")

                        .requestMatchers(HttpMethod.GET, "/oferta_lista/").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/oferta_lista").hasRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.DELETE, "/oferta_lista/**").hasRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.PUT, "/oferta_lista").hasRole("FUNCIONARIO")

                        .requestMatchers(HttpMethod.GET, "/materiais/**").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/materiais/**").hasRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.DELETE, "/materiais/**").hasRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.PUT, "/materiais/**").hasRole("FUNCIONARIO")

                        .requestMatchers(HttpMethod.GET, "/empresas").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/empresas/**").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/empresas/**").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT, "/empresas/**").hasRole("ADMINISTRADOR")

                        .requestMatchers(HttpMethod.GET, "/inventario").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/inventario/**").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.DELETE, "/inventario/**").hasRole("CLIENTE")

                        .requestMatchers(HttpMethod.POST, "/pagamentos/criar_preferencia").hasRole("CLIENTE")

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

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*")); // Libera todas as origens
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(false); // true se precisar enviar cookies/autenticação
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }


}
