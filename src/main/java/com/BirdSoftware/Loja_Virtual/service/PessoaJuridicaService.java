package com.BirdSoftware.Loja_Virtual.service;

import com.BirdSoftware.Loja_Virtual.DTO.EnderecoDTO;
import com.BirdSoftware.Loja_Virtual.DTO.PessoaJuridicaDTO;
import com.BirdSoftware.Loja_Virtual.enums.Role;
import com.BirdSoftware.Loja_Virtual.enums.TipoEndereco;
import com.BirdSoftware.Loja_Virtual.model.Endereco;
import com.BirdSoftware.Loja_Virtual.model.PessoaJuridica;
import com.BirdSoftware.Loja_Virtual.repository.EnderecoRepository;
import com.BirdSoftware.Loja_Virtual.repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaJuridicaService {

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public ResponseEntity<?> cadastraPessoaJuridica(PessoaJuridicaDTO pessoaJuridicaDTO) {

        // Verificar se existe um cadastro PJ
        Optional<PessoaJuridica> existePJ = pessoaJuridicaRepository.findByCnpj(pessoaJuridicaDTO.getCnpj());

        if (existePJ.isPresent()) {
            throw new IllegalArgumentException("Este CNPJ está cadastrado no sistema");
        }

        // Criar nova PJ
        PessoaJuridica pJ = new PessoaJuridica();
        pJ.setNome(pessoaJuridicaDTO.getNome());
        pJ.setEmail(pessoaJuridicaDTO.getEmail());
        pJ.setTelefone(pessoaJuridicaDTO.getTelefone());
        pJ.setCnpj(pessoaJuridicaDTO.getCnpj());
        pJ.setRazaoSocial(pessoaJuridicaDTO.getRazaoSocial());
        pJ.setInscEstadual(pessoaJuridicaDTO.getInscEstadual());
        pJ.setInscMunicipal(pessoaJuridicaDTO.getInscMunicipal());
        pJ.setNomeFantasia(pessoaJuridicaDTO.getNomeFantasia());
        pJ.setCategoria(pessoaJuridicaDTO.getCategoria());

        // **Salvar pJ primeiro para gerar o ID**
        pJ = pessoaJuridicaRepository.save(pJ);

        // Criar e salvar os endereços
        if (pessoaJuridicaDTO.getEnderecos() != null) {
            List<Endereco> enderecos = new ArrayList<>();

            for (EnderecoDTO enderecoDTO : pessoaJuridicaDTO.getEnderecos()) {
                Endereco endereco = new Endereco();
                endereco.setRuaLogra(enderecoDTO.getLogradouro());
                endereco.setNumero(enderecoDTO.getNumero());
                endereco.setBairro(enderecoDTO.getBairro());
                endereco.setCidade(enderecoDTO.getCidade());
                endereco.setUf(enderecoDTO.getEstado());
                endereco.setCep(enderecoDTO.getCep());
                endereco.setComplemento(enderecoDTO.getComplemento());
                endereco.setTipoEndereco(TipoEndereco.COBRANCA);

                // Associar Empresa ao endereço
                endereco.setEmpresa(pJ);

                // Salvar endereço
                enderecoRepository.save(endereco);

                // Adicionar à lista de endereços da PJ
                enderecos.add(endereco);
            }

            // Associar lista de endereços à PJ e atualizar
            pJ.setEndereco(enderecos);
            pessoaJuridicaRepository.save(pJ);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Pessoa Jurídica cadastrada com sucesso!");
    }
}

