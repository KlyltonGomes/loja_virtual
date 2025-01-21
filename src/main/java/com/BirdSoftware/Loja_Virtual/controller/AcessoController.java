package com.BirdSoftware.Loja_Virtual.controller;

import com.BirdSoftware.Loja_Virtual.model.Acesso;
import com.BirdSoftware.Loja_Virtual.service.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acesso")
public class AcessoController {

    @Autowired
    private AcessoService acessoService;


    @PostMapping("/save")
    public ResponseEntity<String> salvarAcesso(@RequestBody Acesso acesso){

        Acesso AcessoSalvo = acessoService.save(acesso);
        return new ResponseEntity<>("Sucesso na operacao: "+acesso.getId(),HttpStatus.CREATED);
    }
}
