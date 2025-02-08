package com.BirdSoftware.Loja_Virtual.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {


// Define a resposta personalizada
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json"); // Certifique-se de definir o tipo de conteúdo como JSON
        response.getWriter().write("{\"error\": \"Acesso negado! Você não tem permissão para acessar este recurso.\"}");


    }
}
