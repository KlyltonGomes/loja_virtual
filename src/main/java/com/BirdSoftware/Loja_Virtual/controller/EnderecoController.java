package com.BirdSoftware.Loja_Virtual.controller;

import com.BirdSoftware.Loja_Virtual.DTO.EnderecoDTO;
import com.BirdSoftware.Loja_Virtual.service.EndercoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EndercoService endercoService;

    @PostMapping("/save")
    public ResponseEntity<String> SalvaEndereco(@RequestBody EnderecoDTO enderecoDTO){

        // Agora, o Controller retorna a resposta do Service diretamente
        ResponseEntity<String> salvaEnd = endercoService.salvarEndereco(enderecoDTO);
        return salvaEnd;  // Retorna a resposta do Service, que já inclui o código e mensagem adequados
    }
}
