package com.BirdSoftware.Loja_Virtual.security;

import com.BirdSoftware.Loja_Virtual.service.CustomAccessDeniedHandler;
import com.BirdSoftware.Loja_Virtual.service.ImplementacaoUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity {

    private final ImplementacaoUserDetailsService userDetailsService;

    public WebConfigSecurity(ImplementacaoUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Desabilita CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()  // Permite acesso à URL de login sem autenticação
                        //.requestMatchers("/usuario/**").permitAll() // Cadastro público
                        //.requestMatchers("/admin/**").hasRole("ADMIN") Apenas Admin acessae "/admin"
                        //.requestMatchers("/usuario/**").hasRole("/usuario") Admin e usuario acessam ("/usuario")
                        //.requestMatchers("/loja/**").hasRole("USER")  Apenas usuários logados
                        .anyRequest().authenticated()  // Exige autenticação para outras URLs
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Usar stateless (sem sessão)
                )
                .formLogin(form -> form.disable())  // Desabilita o formulário de login padrão
                .httpBasic(httpBasic -> httpBasic.disable())  // Desabilita autenticação HTTP básica
                .userDetailsService(userDetailsService)  // Registra o UserDetailsService
                .exceptionHandling(e->e.accessDeniedHandler(new CustomAccessDeniedHandler()))
                .exceptionHandling(e -> e.accessDeniedHandler(new CustomAccessDeniedHandler()))  // Tratamento de erro para acesso negado
                .addFilter(new JWTLoginFilter("/auth/login", authenticationManager(http), passwordEncoder())) // Adiciona o filtro de login JWT
                .addFilterBefore(new JwtApiAuthenticacaoFilter(authenticationManager(http)), UsernamePasswordAuthenticationFilter.class);  // Adiciona o filtro JWT para as requisições subsequentes

        return http.build();  // Chama o método build()
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }
}

