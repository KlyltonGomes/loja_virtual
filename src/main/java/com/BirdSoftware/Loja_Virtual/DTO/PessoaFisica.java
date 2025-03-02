package com.BirdSoftware.Loja_Virtual.DTO;

import java.util.Date;

public class PessoaFisica extends PessoaDTO{
    private String cpf;
    private Date dataNascimento;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
