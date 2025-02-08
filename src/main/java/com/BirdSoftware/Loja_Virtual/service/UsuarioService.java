package com.BirdSoftware.Loja_Virtual.service;

import com.BirdSoftware.Loja_Virtual.model.Usuario;
import com.BirdSoftware.Loja_Virtual.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario buscarUsuarioComAcessos(Long usuarioId) {
        return usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
