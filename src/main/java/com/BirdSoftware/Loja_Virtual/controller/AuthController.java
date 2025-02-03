package com.BirdSoftware.Loja_Virtual.controller;

import com.BirdSoftware.Loja_Virtual.model.Usuario;
import com.BirdSoftware.Loja_Virtual.repository.UsuarioRepository;
import com.BirdSoftware.Loja_Virtual.security.JwttokenAutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwttokenAutenticacaoService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> autenticarUsuario(@RequestBody Usuario usuario, HttpServletResponse response) throws IOException {
        try {
            // Busca o usuário no banco
            Usuario usuarioBanco = usuarioRepository.findUserByLogin(usuario.getLogin());
            if (usuarioBanco == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Usuário não encontrado\"}");
            }

            // Compara a senha fornecida com a senha criptografada no banco
            if (!passwordEncoder.matches(usuario.getSenha(), usuarioBanco.getSenha())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Senha incorreta\"}");
            }

            // Autentica o usuário e gera o token
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha())
            );

            User user = (User) authentication.getPrincipal();
            jwtService.addAuthentication(response, user.getUsername());

            return ResponseEntity.ok().body("{\"message\": \"Login realizado com sucesso\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Erro ao autenticar usuário\"}");
        }
    }
}
