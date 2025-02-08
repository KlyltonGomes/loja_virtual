package com.BirdSoftware.Loja_Virtual.security;

import com.BirdSoftware.Loja_Virtual.ApplicationContextLoad;
import com.BirdSoftware.Loja_Virtual.model.Usuario;
import com.BirdSoftware.Loja_Virtual.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;  // Remover a injeção via construtor

    public JWTLoginFilter(String url, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(url, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        Usuario user ;
        try {
            user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String rawPassword = user.getSenha();
        Usuario usuarioBanco = ApplicationContextLoad.getApplicationContext()
                .getBean(UsuarioRepository.class)
                .findByLogin(user.getLogin())
                .orElseThrow(() -> new BadCredentialsException("Usuário não encontrado"));

        // Comparar a senha fornecida com a senha armazenada no banco de dados
        if (passwordEncoder.matches(rawPassword, usuarioBanco.getSenha())) {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getLogin(), user.getSenha());

            return authenticationManager.authenticate(authenticationToken);
        } else {
            throw new BadCredentialsException("Senha incorreta");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        try {
            String username = authResult.getName();
            //System.out.println(username);
            new JwttokenAutenticacaoService().addAuthentication(response, username);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Erro ao gerar token de autenticação.");
        }
    }
}
