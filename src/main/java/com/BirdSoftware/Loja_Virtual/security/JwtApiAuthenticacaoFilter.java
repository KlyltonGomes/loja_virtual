package com.BirdSoftware.Loja_Virtual.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
/*onde todas as requisicoes serao capturadas para authenticar*/
public class JwtApiAuthenticacaoFilter extends GenericFilterBean {

    private final AuthenticationManager authenticationManager;

    public JwtApiAuthenticacaoFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        Authentication authentication = new JwttokenAutenticacaoService()
                .getAuthentication((HttpServletRequest) request, (HttpServletResponse) response);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}

