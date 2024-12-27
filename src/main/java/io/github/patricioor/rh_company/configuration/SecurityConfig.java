package io.github.patricioor.rh_company.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll() // Permite acesso a todas as rotas sem autenticação
                )
                .httpBasic().disable(); // Desativa a autenticação básica
        return http.build();
    }
}

