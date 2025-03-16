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
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AcessoRepository acessoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> cadastrarLoginSenhaUsuario(UsuarioDTO usuarioDTO) {

        // Verificar se o usuário já existe
        if (usuarioRepository.findByLogin(usuarioDTO.getLogin()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Usuário já existe\"}");
        } else {
            // Criar novo usuário
            Usuario usuario = new Usuario();
            usuario.setLogin(usuarioDTO.getLogin());
            usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha())); // Criptografar a senha
            usuario.setDataAtualSenha(LocalDate.now());

            // Verificar se a role já existe no banco
            Optional<Acesso> acessoOptional = acessoRepository.findByDescricao(Role.ROLE_USER);
            Acesso acesso;

            if (acessoOptional.isPresent()) {
                acesso = acessoOptional.get(); // Usa o já existente
            } else {
                acesso = new Acesso();
                acesso.setDescricao(Role.ROLE_USER);
                acesso = acessoRepository.save(acesso); // Salva apenas se não existir
            }

            // Associar a role ao usuário
            usuario.setAcessos(Collections.singletonList(acesso)); // Lista com um único Acesso compartilhado

            // Salvar o usuário no banco
            usuarioRepository.save(usuario);

            return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Usuário cadastrado com sucesso\"}");
        }
    }
}

