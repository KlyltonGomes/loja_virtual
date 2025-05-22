package com.BirdSoftware.Loja_Virtual.controller;

import com.BirdSoftware.Loja_Virtual.DTO.PessoaJuridicaDTO;
import com.BirdSoftware.Loja_Virtual.service.PessoaJuridicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastrar")
public class PessoaJuridicaController {

    @Autowired
    private PessoaJuridicaService pessoaJuridicaService;

    @PostMapping("/pj")
    public ResponseEntity<?> cadastrarUsuarioPJ(@RequestBody PessoaJuridicaDTO pessoaJuridicaDTO) {

        ResponseEntity<?> resultadoPJ = pessoaJuridicaService.cadastraPessoaJuridica(pessoaJuridicaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultadoPJ);
    }
}
