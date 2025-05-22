package com.BirdSoftware.Loja_Virtual.service;

import com.BirdSoftware.Loja_Virtual.DTO.EnderecoDTO;
import com.BirdSoftware.Loja_Virtual.DTO.PessoaFisicaDTO;
import com.BirdSoftware.Loja_Virtual.enums.TipoEndereco;
import com.BirdSoftware.Loja_Virtual.model.Endereco;
import com.BirdSoftware.Loja_Virtual.model.PessoaFisica;
import com.BirdSoftware.Loja_Virtual.repository.EnderecoRepository;
import com.BirdSoftware.Loja_Virtual.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;


    public ResponseEntity<?> cadastraPessoaFisica (PessoaFisicaDTO pessoaFisicaDTO){

        //verificar se existe algun cadastro igual no banco de dados
        Optional<PessoaFisica> existeCPF = pessoaFisicaRepository.findBycpf(pessoaFisicaDTO.getCpf());

        if (existeCPF.isPresent()){
            throw new IllegalArgumentException("Este CPF está cadastrado no sistema");
        }
        // Criar nova PF
        PessoaFisica pf = new PessoaFisica();
        pf.setNome(pessoaFisicaDTO.getNome());
        pf.setCpf(pessoaFisicaDTO.getCpf());
        pf.setDataNascimento(pessoaFisicaDTO.getDataNascimento());
        pf.setEmail(pessoaFisicaDTO.getEmail());
        pf.setTelefone(pessoaFisicaDTO.getTelefone());

        // **Salvar PF primeiro para gerar o ID**
        pf = pessoaFisicaRepository.save(pf);

        // Criar e salvar os endereços
        if (pessoaFisicaDTO.getEnderecos() != null) {
            List<Endereco> enderecos = new ArrayList<>();

            for (EnderecoDTO enderecoDTO : pessoaFisicaDTO.getEnderecos()) {
                Endereco endereco = new Endereco();
                endereco.setRuaLogra(enderecoDTO.getLogradouro());
                endereco.setNumero(enderecoDTO.getNumero());
                endereco.setBairro(enderecoDTO.getBairro());
                endereco.setCidade(enderecoDTO.getCidade());
                endereco.setUf(enderecoDTO.getEstado());
                endereco.setCep(enderecoDTO.getCep());
                endereco.setComplemento(enderecoDTO.getComplemento());
                endereco.setTipoEndereco(TipoEndereco.ENTREGA);
                // Associar Pessoa Fisica ao endereço
                endereco.setPessoa(pf);

                // Salvar endereço
                enderecoRepository.save(endereco);

                // Adicionar à lista de endereços da PF
                enderecos.add(endereco);
            }
            // Associar lista de endereços à PF e atualizar
            pf.setEndereco(enderecos);
            pessoaFisicaRepository.save(pf);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Pessoa Fisica cadastrada com sucesso!");
    }
}
