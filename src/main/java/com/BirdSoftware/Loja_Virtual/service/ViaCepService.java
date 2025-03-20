package com.BirdSoftware.Loja_Virtual.service;

import com.BirdSoftware.Loja_Virtual.DTO.EnderecoDTO;
import com.BirdSoftware.Loja_Virtual.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class ViaCepService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    private final WebClient webClient;

    public ViaCepService(WebClient webClient) {
        this.webClient = webClient;
    }

    public EnderecoDTO buscarCep(String cep){
        return webClient
                .get()
                .uri(cep+"/json")
                .retrieve()
                .bodyToMono(EnderecoDTO.class)
                .block();// Bloqueia até obter resposta (pode ser substituído por programação reativa)
    }
}
