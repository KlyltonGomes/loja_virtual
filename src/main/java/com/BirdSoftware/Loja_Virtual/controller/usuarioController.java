package com.BirdSoftware.Loja_Virtual.controller;

import com.BirdSoftware.Loja_Virtual.DTO.UsuarioDTO;
import com.BirdSoftware.Loja_Virtual.model.Pessoa;
import com.BirdSoftware.Loja_Virtual.model.Usuario;
import com.BirdSoftware.Loja_Virtual.repository.UsuarioRepository;
import com.BirdSoftware.Loja_Virtual.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class usuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastraUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.cadastrarLoginSenhaUsuario(usuarioDTO);
    }

//    @PostMapping("/atualizarPessoa")
//    public ResponseEntity<String> atualizarPessoa(@RequestBody Pessoa pessoa, @RequestParam Long usuarioId) {
//        // Buscar usuário pelo ID
//        Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);
//
//        // Associar a pessoa ao usuário
//        usuario.setPessoa(pessoa);
//        usuarioService.salvarUsuario(usuario);
//
//        return ResponseEntity.ok("Pessoa atualizada com sucesso!");
//    }


}
