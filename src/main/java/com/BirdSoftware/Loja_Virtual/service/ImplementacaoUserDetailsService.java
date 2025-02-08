package com.BirdSoftware.Loja_Virtual.service;

import com.BirdSoftware.Loja_Virtual.model.Usuario;
import com.BirdSoftware.Loja_Virtual.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*Essa classe é resposavel por carregar o usuario*/
@Service
public class ImplementacaoUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true) // GARANTE QUE A SESSÃO FIQUE ABERTA
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findUserByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}

