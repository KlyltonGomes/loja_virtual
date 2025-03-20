package com.BirdSoftware.Loja_Virtual.controller;

import com.BirdSoftware.Loja_Virtual.DTO.EnderecoDTO;
import com.BirdSoftware.Loja_Virtual.service.ViaCepService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cep")
public class ViaCepController {

    private final ViaCepService viaCepService;

    public ViaCepController(ViaCepService viaCepService) {
        this.viaCepService = viaCepService;
    }

    @GetMapping("/{cep}")
    public EnderecoDTO buscarCep(@PathVariable String cep){
        return viaCepService.buscarCep(cep);
    }
}
