package com.BirdSoftware.Loja_Virtual.security;


import com.BirdSoftware.Loja_Virtual.service.ImplementacaoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity {

    final ImplementacaoUserDetailsService implementacaoUserDetailsService;


    public WebConfigSecurity(ImplementacaoUserDetailsService implementacaoUserDetailsService) {
        this.implementacaoUserDetailsService = implementacaoUserDetailsService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Configuração de autorização para rotas específicas
                // .authorizeHttpRequests(authz -> authz
                //.requestMatchers("/acesso/**").hasRole("ADMIN") // Protege a rota /acesso/** com o papel "ADMIN"
                //.anyRequest().authenticated() // Exige autenticação para todas as outras rotas
                //)
                // Configuração de autorização para rotas específicas
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.POST, "/acesso/save").authenticated()  // Permite POST na rota /acesso/save
                        .anyRequest().permitAll()


                )
                .csrf(csrf -> csrf.disable())  // Desabilita a proteção CSRF na versão 6.x
                .httpBasic(withDefaults()); // Habilita autenticação básica (se necessário);

//                        .requestMatchers(HttpMethod.POST, "/acesso/save").permitAll()  // Permite POST na rota /acesso/save
//                        .requestMatchers(HttpMethod.GET, "/acesso/findAll").permitAll()  // Permite POST na rota /acesso/save
//                        //.anyRequest().permitAll()  // Permite todas as outras requisições sem autenticação
//                )
//                .httpBasic(withDefaults());// Configura autenticação básica


        // Configuração de autorização para rotas específicas
//                .authorizeHttpRequests(authz -> authz
//                        .anyRequest().permitAll() // Permite todas as requisições sem autenticação
//                );

        // Desabilita o login
        //.formLogin(form -> form.disable()) // Não permite o login (nem na interface)

        // Configuração de logout
        //.logout(logout -> logout.permitAll()); // Permite logout sem autenticação

        return http.build(); // Retorna a configuração do SecurityFilterChain
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(implementacaoUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}


