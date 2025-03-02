package com.BirdSoftware.Loja_Virtual.controller;

import com.BirdSoftware.Loja_Virtual.DTO.UsuarioDTO;
import com.BirdSoftware.Loja_Virtual.model.Usuario;
import com.BirdSoftware.Loja_Virtual.repository.UsuarioRepository;
import com.BirdSoftware.Loja_Virtual.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class usuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastraLogin(@RequestBody UsuarioDTO usuarioDTO){
        ResponseEntity<?> novoUser = usuarioService.cadastrarLoginSenhaUsuario(usuarioDTO);
        return ResponseEntity.ok(novoUser);
    }



}
