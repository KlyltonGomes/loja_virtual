package com.BirdSoftware.Loja_Virtual.service;

import com.BirdSoftware.Loja_Virtual.model.Usuario;
import com.BirdSoftware.Loja_Virtual.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void atualizarSenha(String usuario, String novaSenha) {
        // Criptografando a nova senha
        String senhaCriptografada = passwordEncoder.encode(novaSenha);

        // Atualizando a senha no objeto Usuario
        Usuario usuario1 = new Usuario();
        usuario1.setSenha(senhaCriptografada);

        // Salvando o usuário com a senha criptografada
        usuarioRepository.save(usuario1);
    }

}
