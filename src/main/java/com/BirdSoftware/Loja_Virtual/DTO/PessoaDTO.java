package com.BirdSoftware.Loja_Virtual.DTO;

import com.BirdSoftware.Loja_Virtual.model.Endereco;

import java.util.List;

public class PessoaDTO {

    private String nome;
    private String email;
    private String telefone;
    private List<EnderecoDTO> enderecos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<EnderecoDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoDTO> enderecos) {
        this.enderecos = enderecos;
    }
}
