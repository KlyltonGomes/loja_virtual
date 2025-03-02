package com.BirdSoftware.Loja_Virtual.service;

import com.BirdSoftware.Loja_Virtual.DTO.UsuarioDTO;
import com.BirdSoftware.Loja_Virtual.enums.Role;
import com.BirdSoftware.Loja_Virtual.model.Acesso;
import com.BirdSoftware.Loja_Virtual.model.Usuario;
import com.BirdSoftware.Loja_Virtual.repository.AcessoRepository;
import com.BirdSoftware.Loja_Virtual.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AcessoRepository acessoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*criar um login e senha*/
    public ResponseEntity<?> cadastrarLoginSenhaUsuario(UsuarioDTO usuarioDTO) {
        // Verificar se o usuário já existe
        Optional<Usuario> usuarioExistente = usuarioRepository.findByLogin(usuarioDTO.getLogin());
        if (usuarioExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Usuário já existe\"}");
        }

        // Criar novo usuário
        Usuario usuario = new Usuario();
        usuario.setLogin(usuarioDTO.getLogin());
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha())); // Criptografar a senha



        usuarioRepository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Usuário cadastrado com sucesso\"}");
    }
}
