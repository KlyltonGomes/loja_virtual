package com.BirdSoftware.Loja_Virtual.controller;

import com.BirdSoftware.Loja_Virtual.model.Acesso;
import com.BirdSoftware.Loja_Virtual.service.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

@Controller
public class AcessoController {

    @Autowired
    private AcessoService acessoService;

    public Acesso salvarAcesso(Acesso acesso){

        return acessoService.save(acesso);
    }
}
