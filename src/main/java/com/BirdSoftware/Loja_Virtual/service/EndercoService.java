package com.BirdSoftware.Loja_Virtual.service;

import com.BirdSoftware.Loja_Virtual.DTO.EnderecoDTO;
import com.BirdSoftware.Loja_Virtual.enums.TipoEndereco;
import com.BirdSoftware.Loja_Virtual.model.Endereco;
import com.BirdSoftware.Loja_Virtual.repository.EnderecoRepository;
import com.BirdSoftware.Loja_Virtual.repository.PessoaFisicaRepository;
import com.BirdSoftware.Loja_Virtual.repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EndercoService {

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;
    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;


    public ResponseEntity<String> salvarEndereco(EnderecoDTO enderecoDTO){

        // Verificando se já existe o mesmo endereço (CEP + Número)
        Optional<Endereco> enderecoExistente = enderecoRepository.findByCepAndNumero(enderecoDTO.getCep(), enderecoDTO.getNumero());

        if (enderecoExistente.isPresent()) {
            System.out.println("Endereço já existe no banco de dados.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Endereço já existe no banco de dados!");
        }

        /*criar obj a parti dos dados do DTO*/
        Endereco endereco = new Endereco();

        endereco.setRuaLogra(enderecoDTO.getLogradouro());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setUf(enderecoDTO.getEstado());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setTipoEndereco(TipoEndereco.ENTREGA);


        enderecoRepository.save(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body("Endereço salvo no banco de dados");

    }
}
