package com.BirdSoftware.Loja_Virtual.security;


import com.BirdSoftware.Loja_Virtual.service.ImplementacaoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity {

    @Autowired
    private ImplementacaoUserDetailsService implementacaoUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desativa CSRF para APIs REST
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/login").permitAll() // Permite acesso público ao login
                        .anyRequest().authenticated() // Exige autenticação para todas as outras requisições
                )
                .csrf(csrf -> csrf.disable())  // Desabilita a proteção CSRF na versão 6.x
                .logout(logout -> logout
                        .logoutUrl("/logout") // Configura a URL de logout
                        .logoutSuccessUrl("/index") // Redireciona para o index após logout
                )

                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // API sem estado
                .addFilter(new JWTLoginFilter("/login", authenticationManager)) // Adiciona o filtro de login
                .addFilterBefore(new JwtApiAuthenticacaoFilter(), JWTLoginFilter.class) // Adiciona o filtro de autenticação JWT
                .httpBasic(withDefaults()); // Mantém autenticação básica, se necessário

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) throws Exception {
        return new ProviderManager(new DaoAuthenticationProvider() {{
            setUserDetailsService(userDetailsService);
            setPasswordEncoder(passwordEncoder);
        }});
    }
}



