package com.BirdSoftware.Loja_Virtual.controller;

import com.BirdSoftware.Loja_Virtual.DTO.PessoaFisicaDTO;
import com.BirdSoftware.Loja_Virtual.service.PessoaFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastrar")
public class pessoaFisicaController {

    @Autowired
    private PessoaFisicaService pessoaFisicaService;


    @PostMapping("/pessoafisica")
    public ResponseEntity<?> cadastraPessoaFisica(@RequestBody PessoaFisicaDTO pessoaFisicaDTO){

        ResponseEntity<?> resultadoPF = pessoaFisicaService.cadastraPessoaFisica(pessoaFisicaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultadoPF);
    }




}
